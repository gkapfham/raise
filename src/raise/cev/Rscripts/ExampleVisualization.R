source("CoverageEffectiveness.R")
coverage = ReadCoverageData("../../../results/raise/cev/RPRCoverage.dat")
timing = ReadTestTimingData("../../../results/raise/cev/RPOriginalTime.dat")

a = list(c(0:65))

for(i in 1:5)
{
	a = c(a,list(sample(c(0:65))))
}

DisplayCoverageEffectiveness(a,coverage,timing)
