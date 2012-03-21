package CodeAnalyzer;

import CodeAnalyzer.Summary.Individual;
import CodeAnalyzer.Summary.SummaryItem;

import java.util.List;

public class FitnessFunction
{
    public FitnessFunction()
    {
        
    }
    
    public Individual getBestFitness(List<SummaryItem> Example, List<Individual> Generated)
    {
        Individual mostFit = Generated.get(0);
        
        float bestFitness = 0;
        
        for(int Ind=0; Ind < Generated.size(); Ind++)
        {
            Individual current = Generated.get(Ind);
            float Sum = 0;
            for(int i=0;i<Generated.size();i++)
            {
                if(Example.contains(current.getSummary().get(i)))
                {
                    Sum++;
                }
            }
            
            float currentFitness = (Sum/current.getSummary().size()+Sum/Example.size())/2;
            
            if(Ind==0){
                bestFitness = currentFitness;
            }
            
            if(currentFitness > bestFitness)
            {
                mostFit = current;
                bestFitness = currentFitness;
            }
        }
        return mostFit;
    }
}