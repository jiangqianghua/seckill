package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class detail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/jsp/common/tag.jsp");
    _jspx_dependants.add("/WEB-INF/jsp/common/head.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("    <!-- http://127.0.0.1:8080/seckill/seckill/1000/detail -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <title>秒杀详情页</title>\r\n");
      out.write("  ");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<!-- æ° Bootstrap æ ¸å¿ CSS æä»¶ -->\r\n");
      out.write("<link href=\"http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<!-- å¯éçBootstrapä¸»é¢æä»¶ï¼ä¸è¬ä¸ä½¿ç¨ï¼ -->\r\n");
      out.write("<script src=\"http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- HTML5 Shim å Respond.js ç¨äºè®© IE8 æ¯æ HTML5åç´ ååªä½æ¥è¯¢ -->\r\n");
      out.write("<!-- æ³¨æï¼ å¦æéè¿ file://  å¼å¥ Respond.js æä»¶ï¼åè¯¥æä»¶æ æ³èµ·ææ -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("<script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\r\n");
      out.write("<script src=\"https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js\"></script>\r\n");
      out.write("<![endif]-->");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("  <div class=\"panel panel-default text-center\">\r\n");
      out.write("    <div class=\"pannel-heading\">\r\n");
      out.write("      <h1>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seckill.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h1>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"panel-body\">\r\n");
      out.write("      <h2 class=\"text-danger\">\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("        <span class=\"glyphicon glyphicon-time\"></span>\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("        <span class=\"glyphicon\" id=\"seckill-box\"></span>\r\n");
      out.write("      </h2>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("  ");
      out.write("\r\n");
      out.write("  <div id=\"killPhoneModal\" class=\"modal fade\">\r\n");
      out.write("\r\n");
      out.write("    <div class=\"modal-dialog\">\r\n");
      out.write("\r\n");
      out.write("      <div class=\"modal-content\">\r\n");
      out.write("        <div class=\"modal-header\">\r\n");
      out.write("          <h3 class=\"modal-title text-center\">\r\n");
      out.write("            <span class=\"glyphicon glyphicon-phone\"> </span>秒杀电话\r\n");
      out.write("          </h3>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"modal-body\">\r\n");
      out.write("          <div class=\"row\">\r\n");
      out.write("            <div class=\"col-xs-8 col-xs-offset-2\">\r\n");
      out.write("              <input type=\"text\" name=\"killPhone\" id=\"killPhoneKey\" placeholder=\"填写手机号\" class=\"form-control\">\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"modal-footer\">\r\n");
      out.write("          ");
      out.write("\r\n");
      out.write("          <span id=\"killPhoneMessage\" class=\"glyphicon\"> </span>\r\n");
      out.write("          <button type=\"button\" id=\"killPhoneBtn\" class=\"btn btn-success\">\r\n");
      out.write("            <span class=\"glyphicon glyphicon-phone\"></span>\r\n");
      out.write("            Submit\r\n");
      out.write("          </button>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("  </div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<script src=\"http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js\"></script>\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<script src=\"http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"/seckill/resource/script/seckill.js?v=1.14\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("  $(function () {\r\n");
      out.write("    //使用EL表达式传入参数\r\n");
      out.write("    seckill.detail.init({\r\n");
      out.write("      seckillId:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seckill.seckillId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write("      startTime:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seckill.startTime.time}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(",\r\n");
      out.write("      endTime:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seckill.endTime.time}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("  });\r\n");
      out.write("</script>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
