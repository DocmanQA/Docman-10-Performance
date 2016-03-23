package Performance.Performance;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import Performance.Performance.*;



public class DisplayDoc_LoopTest {
	private int Numbers = 10;
	private long Doc_LoadUp_Time_total = 0;
	//private String Browser[] = {"Android","Chrome","IE32"};
	private String Browser[] = {"Chrome","IE32"};
	//private String Browser[] = {"IE32"};
	

  @Test
  public void main() throws FindFailed, IOException{
	  Date date = new Date();
	  SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd_HHmmss");
	  DisplayDoc displaydoc = new DisplayDoc();
	  
	  FileWriter writer;
	  writer = new FileWriter("C:\\Performance_Result\\DisplayDoc_Time_" + ft.format(date) + ".csv");
	  writer.append("Desc,No,Result,Browser" + "\n");
	  for (int x = 0; x < Browser.length ; x++){
		  System.out.println(Browser[x]);
		  Doc_LoadUp_Time_total = 0;
		  
		  for (int i=1; i < (Numbers + 1); i++){
			  System.out.println("Loop" + i);
			  displaydoc.beforelooptest(Browser[x]);
			  displaydoc.main();
			  displaydoc.afterTest();
			  writer.append("Display Document Time" + ',' + i + ',' + displaydoc.Doc_LoadUp_Time + ',' + Browser[x] + '\n');
			  Doc_LoadUp_Time_total += displaydoc.Doc_LoadUp_Time;
			  try {
				  Thread.sleep(4000);
			  } catch (InterruptedException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  }
		  System.out.println("The average display document time is " + (Doc_LoadUp_Time_total / Numbers) );
		  
		  writer.append("Display Document Time" + ',' + "Average" + ',' + (Doc_LoadUp_Time_total / Numbers) + ',' + Browser[x] + '\n');
	  }
      
	  
	  writer.flush();
	  writer.close();  
  }
}
