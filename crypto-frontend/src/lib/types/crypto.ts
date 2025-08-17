export type CryptoQuote = {
  coin: string
  price: number
}

export type CryptoProps = {
  quote: CryptoQuote
  onRemove(): void
}
