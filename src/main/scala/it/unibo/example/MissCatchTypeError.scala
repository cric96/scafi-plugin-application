package it.unibo.example
import ScafiFramework._

//TODO! you can mark every val definition with its aggregate type.
class WrongTypeOnVal extends AggregateProgram {
  override def main(): Any = {
    val nbrMid = nbr(mid)
    nbr(nbrMid) //ERROR !! NOT FOUND!
  }
}
//TODO! no idea...
class CombinationOfArgument extends AggregateProgram {
  def nestedOfNested(x : => Int, y : => Int) : Int = {
    nbr(x + y)
  }

  override def main(): Any = nestedOfNested(nbr(10), nbr(10)) //WRONG!
}
class ComplexDefinition extends AggregateProgram {
  //TODO manage aggregate operator like mux to understand the result type.
  //TODO to resolve argument type, go in deepth in each parameter passed in function call
  def nestedOfNested(x : => Int) : Int = {
    mux(x < 0)(nbr(0))(nbr(x))
  } //type L => F ! WRONG

  override def main(): Any = nestedOfNested(nbr(10)) //WRONG
}
//TODO add check in lambda.
class ArrowTypeCheck extends AggregateProgram {
  override def main(): Any = {
    foldhood(10)((x, y) => nbr(x) + nbr(y))(nbr(mid())) //WRONG! actually
  }
}
//TODO type check is done only inside the aggregate main. extends Typecheck component to check any function/method definition
trait ALib extends Constructs {
  def errorNbr(x : Int) = nbr(nbr(10))
}
