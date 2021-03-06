package com.cts.logfileanaylsispartitioner;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/* 
 * To define a map function for your MapReduce job, subclass 
 * the Mapper class and override the map method.
 * The class definition requires four parameters: 
 *   The data type of the input key
 *   The data type of the input value
 *   The data type of the output key (which is the input key type 
 *   for the reducer)
 *   The data type of the output value (which is the input value 
 *   type for the reducer)
 */

public class LogFileAnaylsisMapper extends Mapper<LongWritable, Text, Text, Text>
{

    /*
     * The map method runs once for each line of text in the input file. The method receives a key of type LongWritable, a value of type Text, and a Context object.
     */
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {

	
	String[] line = value.toString().split(" ");
	//String line = value.toString(); --> This get count without Ignore
	/*
	 * The line.split("\\W+") call uses regular expressions to split the line up by non-word characters.
	 * 
	 * If you are not familiar with the use of regular expressions in Java code, search the web for "Java Regex Tutorial."
	 */
	
	// Use the string tokeniser
	
	/*StringTokenizer words= new StringTokenizer( value.toString().toLowerCase().replaceAll("\\p {Punct}|\\d", ""));
	while (words.hasMoreElements())
	{
		String word=words.nextToken();
	}*/
	
	
	//-----*******************************--------------------- check the lengths like 3 or 4 to avoid run time errors
	    if (line.length > 3)
	    {
        String ip=line[0];
        String monthString=line[3];
        String months[]=monthString.split("/");
        if(months.length>1){
        	String month=months[1].trim();
        	System.out.println("month---"+month);
		/*
		 * Call the write method on the Context object to emit a key and a value from the map method.
		 */
		context.write(new Text(ip), new Text(month));
		//context.write(new Text(word)), new IntWritable(1));  --> Gets duplicates without case
	    }
	    }
	
    }
}