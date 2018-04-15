import Data.Char
main :: IO ()
main = return ()

-- 1) Crie uma função que verifique se um caracter é uma vogal ou não.
isVowel :: Char -> Bool
isVowel v = elem (toLower (v)) "aeiou"

-- 2) Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.
addComma :: [String] -> [String]
addComma str = map(\str -> str++",")str

-- 3) Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML.
-- SEM FUNÇÃO ANÔNIMA
funcHtml :: String -> String
funcHtml html = "<LI>"++html++"</LI>"

htmlListItems :: [String] -> [String]
htmlListItems html = map (funcHtml) html

-- COM FUNÇÃO ANÔNIMA
htmlListItems2 :: [String] -> [String]
htmlListItems2 html = map(\html -> "<LI>"++html++"</LI>")html

-- 4) Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo. Resolva este exercício COM e SEM funções anônimas (lambda).
-- SEM FUNÇÃO ANÔNIMA
funcVogais :: Char -> Bool
funcVogais f = if (isVowel(f) == False) then True else False

semVogais :: String -> String
semVogais s = filter(funcVogais) s 

-- COM FUNÇÃO ANÔNIMA
semVogais2 :: String -> String
semVogais2 s = filter(\x-> not (isVowel x)) s

-- 5) Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string substituindo os demais caracteres por '-', mas mantendo os espaços.
-- SEM FUNÇÃO ANÔNIMA
codificaAux :: Char -> Char
codificaAux x = if (x/=' ') then '-' else x 

codificaStr :: String -> String
codificaStr c = map (codificaAux) c 

-- COM FUNÇÃO ANÔNIMA
codificaStr2 :: String -> String
codificaStr2 c = map (\x -> if x/=' ' then '-' else x) c 

-- 6) Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome. 
firstName:: String -> String
firstName n = takeWhile (/=' ') n

-- 7) Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
isInt :: String -> Bool
isInt i = length (filter (\x-> x<'0' || x>'9') i) == 0

-- 8) Escreva uma função lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu último sobrenome.
lastName :: String -> String
lastName l = last(words l)

-- 9) Escreva uma função userName :: String -> String que, dado o nome completo de uma pessoa, crie um nome de usuário (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome, tudo em minúsculas. 
userName :: String -> String
userName u = map toLower([head(firstName(u))] ++ lastName u)

-- 10) Escreva uma função encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.
encodeChar :: Char -> Char
encodeChar x
  |x == 'a' = '4'
  |x == 'e' = '3'
  |x == 'i' = '2'
  |x == 'o' = '1'
  |x == 'u' = '0'
  |otherwise = x

encodeName :: String -> String
encodeName n = map(encodeChar)n

-- 11) Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00.
betterEncodeChar :: Char -> String
betterEncodeChar x
  |x == 'a' = "4"
  |x == 'e' = "3"
  |x == 'i' = "1"
  |x == 'o' = "0"
  |x == 'u' = "00"
  |otherwise = [x]

betterEncodeName :: String -> String
betterEncodeName n = concatMap(betterEncodeChar)n

-- 12) Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas com '.' até ficarem com 10 caracteres. 
listaStr2 :: String -> String
listaStr2 s
  | length s < 10 = s ++ concat(replicate (10-length s) ".")
  | length s > 10 = take 10 s 
  | otherwise = s

listaStr :: [String] -> [String]
listaStr s = map(listaStr2)s
