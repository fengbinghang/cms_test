/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-01-11 01:36:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class left_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Sidebar -->\r\n");
      out.write("<ul class=\"sidebar navbar-nav\" >\r\n");
      out.write("\t<li class=\"nav-item active\">\r\n");
      out.write("\t<a class=\"nav-link\" href=\"/admin\">\r\n");
      out.write("\t\t\t<i class=\"fas fa-fw fa-tachometer-alt\"></i> <span>后台首页</span>\r\n");
      out.write("\t</a></li>\r\n");
      out.write("\t\r\n");
      out.write("\t<li class=\"nav-item\"><a class=\"nav-link\"  href=\"javascript:void(0)\" data=\"article/selects.do\">\r\n");
      out.write("\t\t\t<i class=\"fas fa-fw fa-folder\"></i> <span>文章管理</span>\r\n");
      out.write("\t</a></li>\r\n");
      out.write("\r\n");
      out.write("\t<li class=\"nav-item\"><a class=\"nav-link\"  href=\"javascript:void(0)\" data=\"user/getUserList.do\">\r\n");
      out.write("\t\t\t<i class=\"fas fa-fw fa-chart-area\"></i> <span>用户管理</span>\r\n");
      out.write("\t</a></li>\r\n");
      out.write("\t<li class=\"nav-item\"><a class=\"nav-link\" href=\"javascript:void(0)\" data=\"待开发\">\r\n");
      out.write("\t\t\t<i class=\"fas fa-fw fa-chart-area\"></i> <span>分类管理</span>\r\n");
      out.write("\t</a></li>\r\n");
      out.write("\t<li class=\"nav-item\"><a class=\"nav-link\" href=\"javascript:void(0)\" data=\"待开发\">\r\n");
      out.write("\t\t\t<i class=\"fas fa-fw fa-table\"></i> <span>系统设置</span>\r\n");
      out.write("\t</a></li>\r\n");
      out.write("\t<li class=\"nav-item\"><a class=\"nav-link\" href=\"javascript:void(0)\" data=\"/links/selects\">\r\n");
      out.write("\t\t\t<i class=\"fas fa-fw fa-table\"></i> <span>友情链接</span>\r\n");
      out.write("\t</a></li>\r\n");
      out.write("</ul>");
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
