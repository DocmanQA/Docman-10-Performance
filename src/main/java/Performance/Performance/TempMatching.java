package Performance.Performance;

import java.util.Calendar;

import org.opencv.core.Core;
import org.opencv.highgui.*;
import org.opencv.imgproc.Imgproc;

import java.util.Date;


public class TempMatching {
	public static void main() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//System.load("C:/OpenCV2/opencv/build/java/x64/opencv_java2412.dll");
		//args[0] = "C:\\OpenCV-3.1\\TempMatch\\Source.png";
		//args[1] = "C:\\OpenCV-3.1\\TempMatch\\Temp.png";
		//args[2] = "C:\\OpenCV-3.1\\TempMatch\\";
		Date BeforeTime = Calendar.getInstance().getTime();
		System.out.println(BeforeTime.getTime());
        //new TempMatch().run("C:\\OpenCV-3.1\\TempMatch\\Source.png", "C:\\OpenCV-3.1\\TempMatch\\Temp.png", "C:\\OpenCV-3.1\\TempMatch\\Result.png", Imgproc.TM_CCOEFF);
        new TempMatch().run("C:\\Performance_Result\\performance_DisDoc_08032016142359.png", "C:\\Performance_Result\\CompareImage\\Doc_1.png", "C:\\Performance_Result\\CompareImage\\Result.png", Imgproc.TM_CCOEFF);
        Date AfterTime = Calendar.getInstance().getTime();
        System.out.println(AfterTime.getTime());
        System.out.println("Duration " + (AfterTime.getTime() - BeforeTime.getTime()));
    }

}
