main :: IO ()
main = return ()

-- 1) Usando recursão, escreva uma função geraTabela :: Int -> [(Int,Int)] que produza uma lista com n tuplas, cada tupla com números de n a 1 e seus respectivos quadrados.
geraTabela :: Int -> [(Int,Int)]
geraTabela 0 = []
geraTabela n = (n,n^2) : geraTabela(n-1)

-- 2) Defina uma função recursiva que verifique se um dado caracter está contido numa string
contidoStr :: Char -> String -> Bool
contidoStr x "" = False
contidoStr y (x:xs) 
  | y == x = True
  | otherwise = contidoStr y xs

-- 3) Defina uma função recursiva que receba uma lista de coordenadas de pontos 2D e desloque esses pontos em 2 unidades
translate :: [(Float,Float)] -> [(Float,Float)]
translate [] = []
translate (x:y) = (fst x+2.0, snd x+2.0) : translate y

-- 4) Defina uma função que receba um número n e retorne uma lista de n tuplas, cada tupla com números de 1 a n e seus respectivos quadrados.
tabelaAux :: Int -> Int -> [(Int,Int)]
tabelaAux x y
  | x <= y = (x,x^2) : tabelaAux (x+1) y 
  | otherwise = []

geraTabela2 :: Int -> [(Int,Int)]
geraTabela2 0 = []
geraTabela2 n = tabelaAux 1 n