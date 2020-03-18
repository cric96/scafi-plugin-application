package it.unibo.example


import ScafiFramework._

trait Lib extends Constructs {
  import Numeric.Implicits._
  //SOME FUNCTION DEFINITION
  def sumHood[A : Numeric](local : => A)(agg : => A) : A = {
    foldhood(local)((a,b) => a + b)(agg)
  } //type (L,F) => L

  def subHood[A : Numeric](local : => A)(agg : => A) : A = {
    foldhood(local)((a,b) => a - b)(agg)
  } //type (L,F) => L

  def nbrMid = nbr(mid()) //type () =>
}
