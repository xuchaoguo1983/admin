/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2015-12-13 13:44:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.errorpage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _404_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;

      out.write("\n");
      out.write("\t\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--[if IE 8]> <html lang=\"en\" class=\"ie8 no-js\"> <![endif]-->\n");
      out.write("<!--[if IE 9]> <html lang=\"en\" class=\"ie9 no-js\"> <![endif]-->\n");
      out.write("<!--[if !IE]><!-->\n");
      out.write("<html lang=\"en\">\n");
      out.write("<!--<![endif]-->\n");
      out.write("<!-- BEGIN HEAD -->\n");
      out.write("<head>\n");
      out.write("<meta charset=\"utf-8\"/>\n");
      out.write("<title>404</title>\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n");
      out.write("<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\">\n");
      out.write("<meta content=\"点钞机数据维护平台\" name=\"description\" />\n");
      out.write("<meta content=\"卓目科技\" name=\"author\" />\n");
      out.write("<!-- BEGIN GLOBAL MANDATORY STYLES -->\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/static/global/plugins/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<!-- END GLOBAL MANDATORY STYLES -->\n");
      out.write("<!-- BEGIN PAGE LEVEL STYLES -->\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/static/pages/css/error.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<!-- END PAGE LEVEL STYLES -->\n");
      out.write("<!-- BEGIN THEME STYLES -->\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/static/global/css/components-rounded.css\" id=\"style_components\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/static/layout/css/layout.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link id=\"style_color\" href=\"");
      out.print(basePath);
      out.write("/static/layout/css/themes/default.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/static/layout/css/custom.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<!-- END THEME STYLES -->\n");
      out.write("<link rel=\"shortcut icon\" href=\"");
      out.print(basePath);
      out.write("/favicon.ico\"/>\n");
      out.write("</head>\n");
      out.write("<body class=\"page-404-full-page\">\n");
      out.write("<div class=\"row\">\n");
      out.write("\t<div class=\"col-md-12 page-404\">\n");
      out.write("\t\t<div class=\"number\">\n");
      out.write("\t\t\t 404\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"details\">\n");
      out.write("\t\t\t<h3>Oops! 没有找到你要访问的页面.</h3>\n");
      out.write("\t\t\t<p>\n");
      out.write("\t\t\t\t 我们在系统里面没有找到您要访问的路径.<br/>\n");
      out.write("\t\t\t\t<a href=\"");
      out.print(basePath);
      out.write("/\">\n");
      out.write("\t\t\t\t返回登陆界面 </a>\n");
      out.write("\t\t\t\t或者联系<a href=\"mailto:admin@zmvision.cn\">管理员</a>.\n");
      out.write("\t\t\t</p>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("<!-- END BODY -->\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
