
import jakarta.servlet.Servlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;



public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);


        Context ctx = tomcat.addWebapp("/", "/home/mikhail/IdeaProjects/webServlet/webServlet2/src/main/webapp");


        Tomcat.addServlet(ctx, "CalculatorServlet", String.valueOf(new CalculatorServlet()));
        ctx.addServletMappingDecoded("/calculator", "CalculatorServlet");


        tomcat.start();
        tomcat.getServer().await();
    }
}