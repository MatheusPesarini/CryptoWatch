'use client'
import type { CryptoProps } from '@/lib/types/crypto'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '../ui/card'
import { useEffect, useRef, useState } from 'react'
import { Button } from '../ui/button'

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
    <Card className="w-56 relative">
      <CardHeader>
        <CardTitle>
          <span>{quote.coin}</span>
          <Button onClick={onRemove}>Remove</Button>
        </CardTitle>
        <CardDescription>{quote.price ? 'Atualizado' : 'Aguardando...'}</CardDescription>
      </CardHeader>
      <CardContent>
        <div
          className={`text-lg font-semibold tabular-nums transition-colors ${flash === 'up'
            ? 'text-green-600'
            : flash === 'down'
              ? 'text-red-600'
              : 'text-foreground'
            }`}
        >
          {priceStr}
        </div>
      </CardContent>
    </Card>
  )
}
