type expr = 
  | Const of int
  | Var of string
  | Plus of args
  | Mult of args
  | Minus of args
  | Div of args
    and
   args = {
    arg1: expr;
    arg2: expr  
  }
  ;;

let rec evaluate = function 
  | Const c -> c 
  | Plus {arg1; arg2} -> (evaluate arg1) + (evaluate arg2)
  | Mult  {arg1; arg2} -> (evaluate arg1) * (evaluate arg2)
  | Minus  {arg1; arg2} -> (evaluate arg1) - (evaluate arg2)
  | Div  {arg1; arg2} -> (evaluate arg1) / (evaluate arg2)
  ;;