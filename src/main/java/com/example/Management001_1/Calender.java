package com.example.Management001_1;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
 
/**
 * Servlet implementation class CalenderAccess
 */
@WebServlet("/CalenderAccess")
public class Calender extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calender() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        prepData(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Calender.jsp");
        //  �t�H���[�h�ɂ��y�[�W�J��
        dispatcher.forward(request, response);
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
    //  ���M�p�̃f�[�^�̍쐬
    private void prepData(HttpServletRequest request){
        // �ϐ�������
        int startday;
        int lastday;
        //  �J�����_�[�̎擾
        Calendar cal = Calendar.getInstance();
        //  �N���ݒ肳��Ă���΁A���̒l���擾�B�����łȂ���΁A���N�̔N��������
        if(request.getParameter("year")==null){
            request.setAttribute("year", cal.get(Calendar.YEAR));   //  ���݂̔N
 
        }else{
            request.setAttribute("year", request.getParameter("year")); //  ���݂̔N
        }
        if(request.getParameter("month")==null){
            request.setAttribute("month", cal.get(Calendar.MONTH)+1);   //  ���݂̌�
        }else{
            request.setAttribute("month", request.getParameter("month"));   //  �^�����ꂽ��
        }
        int year = Integer.parseInt(request.getAttribute("year").toString());
        int month = Integer.parseInt(request.getAttribute("month").toString());
        // �����߂̗j��(��-> 1)
        cal.set(year, month - 1, 1);
        startday = cal.get(Calendar.DAY_OF_WEEK);
        // �����̓��t
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        lastday = cal.get(Calendar.DATE);
        //  �J�����_�[�̃f�[�^���쐬����
        int date = 1;
        int maxday = 6 * 7;
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<th>��</th><th>��</th><th>��</th><th>��</th><th>��</th><th>��</th><th>�y</th>");
        sb.append("</tr>");
        sb.append("<tr>");
        for (int num = 1; num <= maxday; num++) {
            if(num < startday || num > lastday + startday - 1){
                sb.append("<td></td>");
            }else{
                sb.append("<td>"+date+"</td>");
                date++;
            }
            if(num % 7 == 0){
                sb.append("</tr>");
                if(num > startday + lastday - 1){
                    break;
                }
                if(date < lastday){
                    sb.append("<tr>");
                }else{
                    //  �Ōゾ������A���[�v���甲����
                    break;
                }
            }
        }
        sb.append("</table>");
        //  �p�����[�^��ݒ�
        request.setAttribute("calender", sb);
        return;
    }
 
}