'use client'
import { CryptoProps } from '@/lib/types/crypto'
import { Card, CardDescription, CardHeader, CardTitle } from '../ui/card'
import { useEffect, useRef, useState } from 'react'

export function CryptoCard({ quote, onRemove }: CryptoProps) {
  const prevPrice = useRef<number | undefined>(undefined)
  const [flash, setFlash] = useState<'up' | 'down' | null>(null)

  useEffect(() => {
    if (quote.price != null && prevPrice.current != null && prevPrice.current !== quote.price) {
      setFlash(quote.price > prevPrice.current ? 'up' : 'down')
      const t = setTimeout(() => setFlash(null), 500)
      return () => clearTimeout(t)
    }
    if (quote.price != null) prevPrice.current = quote.price
  }, [quote.price])

  const priceStr = quote
    ? quote.price.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })
    : '--'

  return (
    <Card>
      <CardHeader>
        <CardTitle>Noteworthy technology acquisitions 2021</CardTitle>
        <CardDescription>
          Here are the biggest enterprise technology acquisitions of 2021 so far, in reverse
          chronological order.
        </CardDescription>
      </CardHeader>
    </Card>
  )
}
