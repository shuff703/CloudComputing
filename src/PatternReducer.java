import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class PatternReducer 
	extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException{
		
		Configuration conf = context.getConfiguration();
		
		int minSupport = Integer.parseInt(conf.get("minSupport"));
		
		int sum = 0;
		
		for(IntWritable num: values){
			
			sum += num.get();
			
		}
		
		if(sum >= minSupport){
			context.write(key, new IntWritable(sum));
		}
	}

}
