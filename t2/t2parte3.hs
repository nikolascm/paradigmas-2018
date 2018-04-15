import Data.Char
main :: IO ()
main = return ()

-- A codificação EAN-13 é um padrão de código de barras usado em vários tipos de produtos. O número codificado em barras tem 13 dígitos (0-9), sendo o último um dígito verificador (exemplo: 5901234123457, dígito verificador 7). O cálculo do dígito verificador obedece a algumas regras simples disponíveis em: https://www.gs1.org/services/how-calculate-check-digit-manually
-- Você deverá implementar uma função isEanOk :: String -> Bool, que verifique se uma dada string representa um número EAN-13 com dígito verificador válido.

-- Converte string para lista de dígitos
strToInt :: String -> [Int]
strToInt str = map (digitToInt) str

-- Multiplica cada dígito por 1 ou 3, conforme a posĩção 
multLists :: String -> [Int]
multLists str = zipWith (*) (strToInt str) [1,3,1,3,1,3,1,3,1,3,1,3]

-- Retorna soma dos dígitos
returnSum :: String -> Int 
returnSum r = sum (multLists r)

-- Retorna múltiplo mais próximo igual ou superior a dez 
multipleOfTen :: Int -> Int
multipleOfTen x = x + 10 - (x `mod` 10)

-- Função Principal -> Se a diferença entre o múltiplo de 10 mais próximo da soma e a soma == último dígito, então True
isEanOk :: String -> Bool
isEanOk str
  | multipleOfTen (sum (multLists str)) - sum (multLists str) == digitToInt (last str) = True
  | otherwise = False