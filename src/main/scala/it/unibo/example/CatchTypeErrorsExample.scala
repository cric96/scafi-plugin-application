package it.unibo.example
import ScafiFramework._
/* main error category catch by the plugin. */
class NbrError extends AggregateProgram {
  override def main(): Any = nbr(nbr(10)) //error, nbr accepts a local.
}

class RepError extends AggregateProgram {
  override def main(): Any = rep(nbr(10))(x => x) //error, rep accepts a local as first parameter.
}

class FoldhoodError extends AggregateProgram {
  override def main() : Any = {
    foldhood(10)((a,b) => a)(10) //error, foldhood as third parameter accept a field
  }
}

class IfWarning extends AggregateProgram {
  override def main() : Any = if(Math.random() > 0.5) true else false
}

class DefinitionError extends AggregateProgram {
  def intNbr(x : Int) = nbr(x) //type of intNbr : L => F

  override def main(): Any = {
    intNbr(10) //ok
    intNbr(nbr(10)) //error intNbr accept a local
  }
}

class ComplexDefinitionError extends AggregateProgram with Lib {
  def intNbr(x : => Int) = nbr(x) //type L => F

  def sumHoodInt(inital : => Int, expr : => Int) = {
    sumHood(inital)(expr)
  } // type (L,F) => L

  override def main(): Any = {
    sumHoodInt(0, intNbr(mid())) //ok!
    sumHoodInt(nbrMid, intNbr(10)) //ERROR
  }
}

class RecursiveDefinition extends AggregateProgram {
  def recursive[A](local : => A) : A = {
    recursive(local)
  } //type T => T (check in compilation out)

  override def main(): Any = {
    recursive(rep(10)(x => x))
  }
}

class OverloadingDefinition extends AggregateProgram {
  def definitionA(x : =>  Int, y : => Int) : Int = {
    foldhood(y)(_ + _)(x)
  }
  def definitionA(x : => Double) : Double = nbr(x)

  override def main(): Any = {
    definitionA(10)
    definitionA(nbr(20), 10)
    definitionA(nbr(10)) //ERROR
    definitionA(20, 10) //ERROR
  }
}
class CombinationOfNbr extends AggregateProgram {
  override def main(): Any = nbr(nbr(10) + nbr(20))
}

class NestedMux extends AggregateProgram {
  override def main() : Any = foldhood[Int](
    mux(true)(nbr(10))(10)
  ) {
    _ + _
  } {
    nbr(mid())
  }
}