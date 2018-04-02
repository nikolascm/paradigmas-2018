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