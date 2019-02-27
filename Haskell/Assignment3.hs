{-
 - Starting code for Assignment 3.
 - You should add the required functions plus your own
 - helper functions to this file and submit it to OnQ.
 - CISC 260, Winter 2018
 
   CISC 260, winter 2018
   Queen's University, Kingston, Ontario
   author: TONG CHEN 10189689, 14tc41 

 -}
module Assignment3 where

-- Definition of the Trie type.  A trie consists of a starting
-- letter (the special character '.' in a complete trie plus 
-- list of sub-tries.  
data Trie = MakeTrie Char [Trie] 
    deriving Eq

-- As a starting point, emptyTrie is a trie containing
-- nothing but the empty string
emptyTrie = MakeTrie '.' [MakeTrie '$' []]

emptyTrieN = MakeTrie '.' []


    
-- showTrie creates a multi-line string describing a trie in 
-- indented form (very much like the show function for indented 
-- representations of the family trees we discussed in class)
-- There will be an extra newline at the beginning of the result.
showTrie :: Int -> Trie -> String
showTrie level (MakeTrie c []) = "\n" ++ (indent level) ++ [c]
showTrie level (MakeTrie c [subTrie]) = 
    "\n" ++ (indent level) ++ c:(showTrie (level+1) subTrie)
showTrie level (MakeTrie c subTrieList) = 
    "\n" ++ (indent level) ++ 
    c:concat (map (showTrie (level+1)) subTrieList)
    
-- Helper function for showTrie: indent n returns a string with 
-- 2*n spaces in it.
indent :: Int -> String
indent n
    | n <= 0 = ""
    | otherwise = "  " ++ (indent (n-1))

-- Specify how tries will be displayed by the interpreter
instance Show Trie 
    where show t = tail (showTrie 0 t) -- getting rid of extra 
                                       -- newline at beginning
                                       -- of the showTrie result

-- A simple trie containing "cat", "can" and "dog".  You can use -- this trie for initial testing before you've written functions 
-- to help you create larger tries.
simpleTrie = 
    MakeTrie '.' [
        MakeTrie 'c' [
            MakeTrie 'a' [
                lastLetter 'n',
                lastLetter 't'
                ]
            ],
        MakeTrie 'd' [
                MakeTrie 'o' [
                    lastLetter 'g'
                ]
            ]
        ]

-- helper to make the above shorter:
-- a trie containing a single character at the end of a word 
lastLetter ch = MakeTrie ch [MakeTrie '$' []]
endingTrie = MakeTrie '$' []

searchWord :: String -> Trie -> Bool
searchWord word (MakeTrie letter subTrieList) = or (map (searchString word) subTrieList)

searchString :: String -> Trie -> Bool
searchString [] _ = False
searchString (x:xs) (MakeTrie letter subTrie)
    | xs == [] && x == letter && last = True
    | x == letter = searchWord xs (MakeTrie letter subTrie)
    | otherwise = False
    where 
        last = ['$'] == [lttr | (MakeTrie lttr y) <- subTrie, lttr == '$']

wordsInTrie :: Trie -> [String]
wordsInTrie (MakeTrie letter subTrie) = concat(map stringInTrie subTrie)

stringInTrie :: Trie -> [String]
stringInTrie (MakeTrie letter []) = [[]]
stringInTrie (MakeTrie letter subTrie) = map (letter :) (concat(map stringInTrie subTrie))

addWordToTrie :: String -> Trie -> Trie
addWordToTrie word cmpltTrie 
    | (searchWord word cmpltTrie) = cmpltTrie
    | otherwise = addStringToTrie word cmpltTrie

addStringToTrie :: String -> Trie -> Trie
addStringToTrie (xs) (MakeTrie letter subTrie)
    | xs == [] && letter /= '$' = (MakeTrie letter ([(MakeTrie '$' [])] ++ subTrie))
    | xs /= [] && subTrie == [endingTrie] =  MakeTrie letter (subTrie ++ (createSubTrie (xs))) 
    | [] /= subTrieMatch =  MakeTrie letter (leqSubTrie ++ [addStringToTrie (tail xs) (head [atrie|atrie <-subTrieMatch, atrie /= endingTrie])] ++ gteSubTrie)
    | [] == gteSubTrie = MakeTrie letter (subTrie ++ (createSubTrie (xs)))
    | otherwise = MakeTrie letter (leqSubTrie ++ (createSubTrie (xs)) ++ gteSubTrie)
    where
        gteSubTrie = [(MakeTrie lttr subList) | (MakeTrie lttr subList) <- subTrie, xs /= [] && lttr > (head xs) ]
        subTrieMatch = [ (MakeTrie lttr subList) | (MakeTrie lttr subList) <- subTrie, xs /= [] && lttr == (head xs)]
        leqSubTrie = [(MakeTrie lttr subList) | (MakeTrie lttr subList) <- subTrie, xs /= [] && lttr < (head xs) ]
        
createSubTrie :: String -> [Trie]
createSubTrie (x:xs)
    | xs == [] = [lastLetter x]
    | otherwise = [MakeTrie x (createSubTrie xs)]
        
createTrie :: [String] -> Trie
createTrie list = foldr addWordToTrie emptyTrieN list

-- The trie from the picture on the assignment page.  (Note that -- "cat" is added twice and will not result in duplication in the 
-- trie.)  Uncomment this example for more help with testing
-- after you have written the createTrie function.
webTrie = createTrie ["catnip", "cat", "dog", "cab", "dime",                        "dim", "candy", "cat", "catalog", "do"]

 
    
    

