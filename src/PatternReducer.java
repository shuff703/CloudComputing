import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class PatternReducer 
	extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
	
	@Override
	public void reduce(Text key, Iterable<DoubleWritable> values, Context context) 
			throws IOException, InterruptedException{
		
		Configuration conf = context.getConfiguration();
		
		int minSupport = Integer.parseInt(conf.get("minSupport"));
		
		double sum = 0;
		double total = conf.getDouble("totalTransactions", -1);
		
		for(DoubleWritable num: values){
			
			sum += num.get();
			
		}
		
		if(sum >= minSupport){
			context.write(key, new DoubleWritable(sum/total));
			StringBuilder sb = new StringBuilder(conf.get("itemSets"));
			sb.append(key+":"+sum+",");
			conf.set("itemSets", sb.toString());
		}
	}

}
