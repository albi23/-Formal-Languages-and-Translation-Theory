#!/bin/bash
bison calc.y --file-prefix=MyCalcBison
jflex  jflex_calc.jflex
javac MyCalc.java  MyCalcBison.java
