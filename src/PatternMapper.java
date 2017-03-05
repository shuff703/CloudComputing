import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

public class PatternMapper 
	extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		String line = value.toString();
		line.replaceAll("([/(/)\t])", "");
		ArrayList<String> subsets = new ArrayList<>();
		String [] values = line.split(",");
		
        for (int i = 0; i < (1<<values.length); i++){
            StringBuilder sb = new StringBuilder();
 
            for (int j = 0; j < values.length; j++){
 
                if ((i & (1 << j)) > 0){
                	sb.append(values[j]+",");
                }
            subsets.add(sb.toString());
            }
        }
        
        for(String set: subsets){
        	
        	context.write(new Text(set), new IntWritable(1));
        	
        }

	}

}
