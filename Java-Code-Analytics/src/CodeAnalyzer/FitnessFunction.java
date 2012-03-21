package CodeAnalyzer.Summary;

import CodeAnalyzer.Summary.SummaryItem;
import java.util.list;

public class FitnessFunction
{
    public FitnessFunction(){
        
    }
    public Individual getBestFitness(List<SummaryItem> Example,List<Individual> Generated)
    {
        Individual mostFit = Generated.get(0);
        float bestFitness = 0;
        for(int Ind=0; Ind < Generated.size(); Ind++){
            Individual current = Generated.get(Ind);
            float Sum = 0;
            for(int i=0;i<Generated.size();i++){
                if(Example.getSummary().contains(current.getSummary().get(i))){
                    Sum++;
                }
            }
            float currentFitness = (Sum/t+Sum/p)/2;
            if(Ind==0){
                bestFitness = currentFitness;
            }
            if(currentFitness > bestFitness){
                this.mostFit = current;
                bestFitness = currentFitness;
            }
        }
        return mostFit;
    }
}