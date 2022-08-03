<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.article.dto.ArticleDto" %>

<%
    List<ArticleDto> articles = (List<ArticleDto>)request.getAttribute("articles");
%>
<script src="https://cdn.tailwindcss.com"></script> <!--// 테일윈드 불러오기-->

<%@ include file="../common/head.jspf"%>
<!--보통 구획은 섹션으로 나눈다-->
<section>
    <!--contatiner : 너비가 너무 넓게 퍼지는 것을 막는다 + 반응형은 잃지 않는다-->
    <!--mx-auto : margin-left:auto; margin-right:auto; block요소 가운데 정렬하는 것-->
    <div class="container px-3 mx-auto"> <!--//container는 내용이 줄어든다 (최대너비를 줄인다) mx-auto하면 가운데로 간다-->
        <h1 class="font-bold text-lg">게시물 리스트</h1>
        <ul class="mt-5">
            <% for ( ArticleDto article : articles ) { %>
            <li class="flex">  <!--//간격 사라짐-->
                <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/article/detail/free/<%=article.getId()%>"><%=article.getId()%></a>
                <a class="flex-grow hover:underline hover:text-[red]" href="/usr/article/detail/free/<%=article.getId()%>"><%=article.getTitle()%></a><!--//flex-grow는 최대한 넓힌다 성장성 1주는 것-->
                <a onclick="if(!confirm('정말로 삭제하시겠습니까?')) return false;" class="w-[40px] hover:underline hover:text-[red]" href="/usr/article/delete/free/<%=article.getId()%>">삭제</a>
                <a class=" hover:underline hover:text-[red]" href="/usr/article/modify/free/<%=article.getId()%>">수정</a>
            </li>
            <% } %>
        </ul>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>
