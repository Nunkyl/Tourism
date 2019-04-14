package reporting;

import java.io.File;

/**
 * Created by eliza on 18.03.19.
 */
public interface ReportGenerator {

    File createReport() throws Exception;
}
