'use client'

import { useEffect, useRef, useState } from 'react'
import type { CryptoQuote } from '@/lib/types/crypto'

type QuoteMap = Record<string, CryptoQuote>

export function useCryptoFeed() {
  const apiUrl = process.env.NEXT_PUBLIC_API_URL
  console.log('API_URL:', apiUrl)

  const [quotes, setQuotes] = useState<QuoteMap>({})
  const [status, setStatus] = useState<'connecting' | 'open' | 'closed' | 'error'>('connecting')
  const wsRef = useRef<WebSocket | null>(null)

  useEffect(() => {
    if (!apiUrl) {
      setStatus('error')
      console.error('NEXT_PUBLIC_API_URL ausente')
      return
    }

    setStatus('connecting')
    const ws = new WebSocket(apiUrl)
    wsRef.current = ws

    ws.onopen = () => setStatus('open')
    ws.onerror = () => setStatus('error')
    ws.onclose = () => setStatus('closed')
    ws.onmessage = (ev) => {
      try {
        const data = JSON.parse(ev.data)
        const list = Array.isArray(data) ? data : [data]
        setQuotes((prev) => {
          const next = { ...prev }
          for (const q of list) {
            if (!q.coin) continue
            next[q.coin] = q
          }
          return next
        })
      } catch (error) {
        console.log('Erro no hook da crypto', error)
      }
    }

    return () => ws.close()
  }, [apiUrl])

  return { quotes, status }
}
