source("CoverageEffectiveness.R")
library(gtools)

coverage = ReadCoverageData("../../../../data/raise/reduce/examples/CoverageData_IST.txt")
timing = ReadTestTimingData("../../../../data/raise/reduce/examples/TestTimingData_IST.txt")

#a = list(c(1:4))
orderList = list()
#for(i in 1:5)
#{
#	a = c(a,list(sample(c(1:4))))
#}

a = permutations(4,4,1:4)

for ( i in 1:24)
    {

 #     CurrentCEValue =
  #      CalculateCoverageEffectiveness(AllOrderings[i,],
 #                                      Coverage, Timing,
  #                                     Graph=AllGraphs)

   #   TotalOrderingsJustNames =
    #    c(TotalOrderingsJustNames, paste(AllOrderings[i,],
#                                         collapse=", "))
      
     # AllOrderingsCoverageEffectiveness =
      #  c(AllOrderingsCoverageEffectiveness, CurrentCEValue)

      orderList = c(orderList,list(a[i,]))
    }


DisplayCoverageEffectiveness(orderList,coverage,timing)
