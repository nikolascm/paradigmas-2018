import Data.Char
main :: IO ()
main = return ()

-- 1) Escreva uma função recursiva isBin :: String -> Bool para verificar se uma dada String representa um número binário, ou seja, contém apenas caracteres '0' ou '1'.
isChar :: Char -> Bool
isChar c
  | c == '0' || c == '1' = True 
  | otherwise =  False

myLength :: String -> Int 
myLength "" = 0
myLength (x:xs)
  | xs /= "" = myLength xs + 1
  | otherwise = 1 

isBin :: String -> Bool
isBin "" = False
isBin (x:xs)
  | (isChar x) && (myLength xs > 0) = isBin xs
  | (isChar x) && (myLength xs == 0) = True
  | otherwise = False

-- 2) Reescreva a função acima de forma não-recursiva. Dê outro nome para ela, por exemplo isBin'. 
isBin' :: String -> Bool
isBin' "" = False
isBin' str = if(length(filter (\x -> (x/='0' && x/='1'))str)==0) then True else False

-- 3) Encontra-se abaixo a definição parcial da função bin2dec :: [Int] -> Int, que converte uma lista de 0's e 1's (representando um número binário), em seu equivalente em decimal.

--bin2dec :: [Int] -> Int
--bin2dec [] = undefined
--bin2dec bits = auxBin2Dec bits ((length bits)-1)

-- Observe que:
-- Usou-se undefined para o caso em que a função não tem resultado definido.
-- Usou-se uma função auxiliar (auxBin2Dec) que recebe, como segundo argumento, o expoente que deverá multiplicar o primeiro elemento da lista.

-- Implemente a função auxBin2Dec de forma recursiva, para que bin2dec funcione corretamente
auxBin2Dec :: [Int] -> Int -> Int
auxBin2Dec [] e = 0
auxBin2Dec (x:xs) e = x * (2^e) + auxBin2Dec xs (e-1)

bin2dec :: [Int] -> Int
bin2dec [] = 0
bin2dec bits = auxBin2Dec bits ((length bits)-1)
--bin2dec bits = bin2dec' bits ((length bits)-1)

-- 4) Reescreva a função do exercício anterior de forma não-recursiva, usando funções pré-definidas em Haskell. Dê outro nome para a função (por exemplo, bin2dec').

-- 5) Crie uma função recursiva dec2bin :: Int -> [Int] que receba um número inteiro positivo e retorne sua representação em binário, sob forma de uma lista de 0's e 1's. As funções auxiliares autorizadas aqui são mod, div e reverse.
dec2bin :: Int -> [Int]
dec2bin 0 = []
dec2bin y = y `mod` 2 : dec2bin (y `div` 2)

dec2bin' :: Int -> [Int]
dec2bin' x = reverse (dec2bin x)

-- 6) Implemente uma dessas funções: isHex :: String -> Bool ou hex2dec :: String -> Int ou dec2hex :: Int -> String, que são semelhantes às dos exercícios anteriores, porém com números hexadecimais no lugar de números binários.
auxHex2dec :: String -> Int
auxHex2dec [] = 0 
auxHex2dec (x:xs) = digitToInt x * (16^(length xs)) + auxHex2dec xs 

hex2dec :: String -> Int
hex2dec [] = 0
hex2dec bits = auxHex2dec bits