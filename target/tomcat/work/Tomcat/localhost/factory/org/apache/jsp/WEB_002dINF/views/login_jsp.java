/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2015-12-15 00:44:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--[if IE 8]> <html lang=\"en\" class=\"ie8 no-js\"> <![endif]-->\n");
      out.write("<!--[if IE 9]> <html lang=\"en\" class=\"ie9 no-js\"> <![endif]-->\n");
      out.write("<!--[if !IE]><!-->\n");
      out.write("<html lang=\"en\">\n");
      out.write("<!--<![endif]-->\n");
      out.write("<!-- BEGIN HEAD -->\n");
      out.write("<head>\n");
      out.write("<base href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basePath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\n");
      out.write("<meta charset=\"utf-8\" />\n");
      out.write("<title>点钞机数据维护平台</title>\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\n");
      out.write("<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\">\n");
      out.write("<meta content=\"点钞机数据维护平台\" name=\"description\" />\n");
      out.write("<meta content=\"卓目科技\" name=\"author\" />\n");
      out.write("<!-- BEGIN GLOBAL MANDATORY STYLES -->\n");
      out.write("<link href=\"static/global/plugins/font-awesome/css/font-awesome.min.css\"\n");
      out.write("\trel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<link href=\"static/global/plugins/bootstrap/css/bootstrap.min.css\"\n");
      out.write("\trel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<link href=\"static/global/plugins/uniform/css/uniform.default.css\"\n");
      out.write("\trel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<!-- END GLOBAL MANDATORY STYLES -->\n");
      out.write("<!-- BEGIN PAGE LEVEL STYLES -->\n");
      out.write("<link href=\"static/pages/css/login.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<!-- END PAGE LEVEL SCRIPTS -->\n");
      out.write("<!-- BEGIN THEME STYLES -->\n");
      out.write("<link href=\"static/global/css/components-rounded.css\"\n");
      out.write("\tid=\"style_components\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<link href=\"static/global/css/plugins.css\" rel=\"stylesheet\"\n");
      out.write("\ttype=\"text/css\" />\n");
      out.write("<link href=\"static/layout/css/layout.css\" rel=\"stylesheet\"\n");
      out.write("\ttype=\"text/css\" />\n");
      out.write("<link href=\"static/layout/css/themes/default.css\" rel=\"stylesheet\"\n");
      out.write("\ttype=\"text/css\" id=\"style_color\" />\n");
      out.write("<link href=\"static/layout/css/custom.css\" rel=\"stylesheet\"\n");
      out.write("\ttype=\"text/css\" />\n");
      out.write("<!-- END THEME STYLES -->\n");
      out.write("<link rel=\"shortcut icon\" href=\"favicon.ico\" />\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("<!--\n");
      out.write("\tvar _basePath = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basePath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\n");
      out.write("//-->\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("<!-- END HEAD -->\n");
      out.write("<!-- BEGIN BODY -->\n");
      out.write("<body class=\"login\">\n");
      out.write("\t<!-- BEGIN SIDEBAR TOGGLER BUTTON -->\n");
      out.write("\t<div class=\"menu-toggler sidebar-toggler\"></div>\n");
      out.write("\t<!-- END SIDEBAR TOGGLER BUTTON -->\n");
      out.write("\t<!-- BEGIN LOGO -->\n");
      out.write("\t<div class=\"logo\">\n");
      out.write("\t\t<img alt=\"\" src=\"static/pages/img/logo.png\">\n");
      out.write("\t</div>\n");
      out.write("\t<!-- END LOGO -->\n");
      out.write("\t<!-- BEGIN LOGIN -->\n");
      out.write("\t<div class=\"content\">\n");
      out.write("\t\t<!-- BEGIN LOGIN FORM -->\n");
      out.write("\t\t<form class=\"login-form\" action=\"system/auth/login\" method=\"post\">\n");
      out.write("\t\t\t<h3 class=\"form-title\">点钞机数据维护系统</h3>\n");
      out.write("\t\t\t<div class=\"alert alert-danger display-hide\">\n");
      out.write("\t\t\t\t<button class=\"close\" data-close=\"alert\"></button>\n");
      out.write("\t\t\t\t<span>请输入正确的登陆信息. </span>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->\n");
      out.write("\t\t\t\t<label class=\"control-label visible-ie8 visible-ie9\">用户名</label>\n");
      out.write("\t\t\t\t<div class=\"input-icon right\">\n");
      out.write("\t\t\t\t\t<i class=\"fa\"></i> <input\n");
      out.write("\t\t\t\t\t\tclass=\"form-control form-control-solid placeholder-no-fix\"\n");
      out.write("\t\t\t\t\t\ttype=\"text\" autocomplete=\"off\" placeholder=\"用户名\" name=\"username\"\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${username }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" required />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t<label class=\"control-label visible-ie8 visible-ie9\">密码</label>\n");
      out.write("\t\t\t\t<div class=\"input-icon right\">\n");
      out.write("\t\t\t\t\t<i class=\"fa\"></i><input\n");
      out.write("\t\t\t\t\t\tclass=\"form-control form-control-solid placeholder-no-fix\"\n");
      out.write("\t\t\t\t\t\ttype=\"password\" autocomplete=\"off\" placeholder=\"密码\"\n");
      out.write("\t\t\t\t\t\tname=\"password\" required />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t<label class=\"control-label visible-ie8 visible-ie9\">验证码</label>\n");
      out.write("\t\t\t\t<div class=\"input-group verifycode\">\n");
      out.write("\t\t\t\t\t<div class=\"input-icon right\">\n");
      out.write("\t\t\t\t\t\t<i class=\"fa\"></i> <input\n");
      out.write("\t\t\t\t\t\t\tclass=\"form-control form-control-solid placeholder-no-fix\"\n");
      out.write("\t\t\t\t\t\t\ttype=\"text\" autocomplete=\"off\" placeholder=\"验证码\"\n");
      out.write("\t\t\t\t\t\t\tname=\"verifycode\" required />\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<img id=\"verifycodeImg\" title=\"点击刷新\" src=\"system/auth/verifycode\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"form-actions\">\n");
      out.write("\t\t\t\t<button type=\"submit\" class=\"btn btn-success\">登陆</button>\n");
      out.write("\t\t\t\t<label class=\"rememberme check\"> <input type=\"checkbox\"\n");
      out.write("\t\t\t\t\tname=\"remember\" value=\"Y\" />记住我\n");
      out.write("\t\t\t\t</label>\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t</form>\n");
      out.write("\t\t<!-- END LOGIN FORM -->\n");
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"copyright\">2014 © 卓目科技.</div>\n");
      out.write("\t<!-- END LOGIN -->\n");
      out.write("\t<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->\n");
      out.write("\t<!-- BEGIN CORE PLUGINS -->\n");
      out.write("\t<!--[if lt IE 9]>\n");
      out.write("<script src=\"static/global/plugins/respond.min.js\"></script>\n");
      out.write("<script src=\"static/global/plugins/excanvas.min.js\"></script> \n");
      out.write("<![endif]-->\n");
      out.write("\t<script src=\"static/global/plugins/jquery.min.js\"\n");
      out.write("\t\ttype=\"text/javascript\"></script>\n");
      out.write("\t<script src=\"static/global/plugins/jquery-migrate.min.js\"\n");
      out.write("\t\ttype=\"text/javascript\"></script>\n");
      out.write("\t<script src=\"static/global/plugins/bootstrap/js/bootstrap.min.js\"\n");
      out.write("\t\ttype=\"text/javascript\"></script>\n");
      out.write("\t<script src=\"static/global/plugins/uniform/jquery.uniform.min.js\"\n");
      out.write("\t\ttype=\"text/javascript\"></script>\n");
      out.write("\t<!-- END CORE PLUGINS -->\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"static/global/plugins/jquery-validation/js/jquery.validate.min.js\"\n");
      out.write("\t\ttype=\"text/javascript\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"static/global/plugins/jquery-validation/js/localization/messages_zh.min.js\"\n");
      out.write("\t\ttype=\"text/javascript\"></script>\n");
      out.write("\t<script src=\"static/global/plugins/jquery.md5.js\"\n");
      out.write("\t\ttype=\"text/javascript\"></script>\n");
      out.write("\t<script src=\"static/global/scripts/formvalidate.js\"\n");
      out.write("\t\ttype=\"text/javascript\"></script>\n");
      out.write("\t<script src=\"static/global/scripts/metronic.js\" type=\"text/javascript\"></script>\n");
      out.write("\t<!-- BEGIN PAGE LEVEL SCRIPTS -->\n");
      out.write("\t<script src=\"static/pages/scripts/login.js\" type=\"text/javascript\"></script>\n");
      out.write("\t<!-- END PAGE LEVEL SCRIPTS -->\n");
      out.write("\t<script>\n");
      out.write("\t\tjQuery(document).ready(function() {\n");
      out.write("\t\t\tMetronic.init(); // init metronic core components\n");
      out.write("\t\t\tLogin.init();\n");
      out.write("\t\t});\n");
      out.write("\t</script>\n");
      out.write("\t<!-- END JAVASCRIPTS -->\n");
      out.write("\n");
      out.write("\t<!-- error handling -->\n");
      out.write("\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("</body>\n");
      out.write("<!-- END BODY -->\n");
      out.write("</html>\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/views/login.jsp(153,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty error}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t<script type=\"text/javascript\">\n");
        out.write("\t\t\tif ('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error.code}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("' != 0) {\n");
        out.write("\t\t\t\t$('.alert-danger span').html('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error.message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("');\n");
        out.write("\t\t\t\t$('.alert-danger').show();\n");
        out.write("\t\t\t}\n");
        out.write("\t\t</script>\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }
}
