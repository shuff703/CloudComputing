import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;

public class AssociationReducer 
	extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
	
	public void reduce(Text key, Iterable<DoubleWritable> support, Context context) throws IOException, InterruptedException{
		
		Configuration conf = context.getConfiguration();
		
		String itemSets = conf.get("itemSets");
		
		String [] items = key.toString().split(",");
		
		for(DoubleWritable value: support){
			double intersectSupport = Double.parseDouble(value.toString());
			
			for (int i = 0; i < (1<<items.length); i++){
	            StringBuilder given = new StringBuilder();
	 
	            for (int j = 0; j < items.length; j++){
	 
	                if ((i & (1 << j)) > 0){
	                	given.append(items[j]+",");
	                }
	            }
	            if(itemSets.contains(given)){
	            	
	            	StringBuilder implies = new StringBuilder();
	            	for(String item : items){
	            		if(given.toString().contains(item) == false){
	            			implies.append(item);
	            		}
	            	}
	            	double givenSupport = Double.parseDouble(itemSets.substring(itemSets.indexOf(given.toString())
	            			,itemSets.indexOf(",", itemSets.indexOf(given.toString())+1)));
	            	context.write(new Text(given.toString()+"->"+implies.toString()), new DoubleWritable(intersectSupport/givenSupport));
	            }
			}
        }
		
		
	}
}
