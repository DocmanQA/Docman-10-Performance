package Performance.Performance;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
//import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

public class TempMatch {
	
	public Point matchLoc;
	
	public double[] run(String inFile, String templateFile, String outFile, int match_method) {
        System.out.println("\nRunning Template Matching");

        Mat img = Highgui.imread(inFile);
        Mat templ = Highgui.imread(templateFile);

        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        // / Do the Matching and Normalize
        Imgproc.matchTemplate(img, templ, result, match_method);
        //Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        

        // / Localizing the best match with minMaxLoc
        MinMaxLocResult mmr = Core.minMaxLoc(result);
        
        //Point matchLoc;
        double Value = 1;
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
            
        } else {
            matchLoc = mmr.maxLoc;
            Value = mmr.maxVal;
            System.out.println(Value);
        }
        
        System.out.println(matchLoc);
        System.out.println(templ.cols());

        // / Show me what you got
        Core.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()), new Scalar(0, 0, 255));
        

        // Save the visualized detection.
        System.out.println("Writing "+ outFile);
        Highgui.imwrite(outFile, img);
        double[] a = {matchLoc.x, matchLoc.y, Value};
        return a;
    }

	private Object DisplayDocwithTempMatch() {
		// TODO Auto-generated method stub
		return null;
	}

}

