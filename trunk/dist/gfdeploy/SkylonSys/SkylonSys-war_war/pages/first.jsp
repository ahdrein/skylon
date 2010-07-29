<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        
        <c:out value="${pageContext}"/><br/>
        <%--
        <c:forEach var="entry" items="${pageScope}" varStatus="conta">
            <tr><td>
                <c:out value="${entry}"/>
            </td></tr>
        </c:forEach>
        --%>
        
        <h:inputText required="true" id="dd" ></h:inputText>
        
        <h:panelGrid columns="2">
            <h:outputText value="Descricao Curta" />
            <h:outputText value="#{products.firstProduct.sDescCurtaProd}" />
        </h:panelGrid>

         <rich:dataTable>
                <rich:columnGroup>
                    <rich:columns value="TESTE">
                        <rich:column>A</rich:column>
                    </rich:columns>
                    <rich:columns value="TESTE">
                        <rich:column>B</rich:column>
                        
                    </rich:columns>    
                </rich:columnGroup>
            </rich:dataTable>
            
        <table>
            <c:forEach var="produtos" items="${products.products}" varStatus="conta">
                <tr>
                    <rich:panel>
                        <td width="10">
                            <c:out value="${conta.count}" />
                        </td>
                        <td width="50%">
                            <c:out value="${produtos}" />
                        </td>
                        <td>
                            <c:out value="${produtos}" />
                        </td>
                    </rich:panel>    
                </tr>
            </c:forEach>
            
   
            
                    <%-- Data TAble  --%>
                    <%--

                <rich:dataTable 
                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                    cellpadding="0" cellspacing="0" 
                    width="700" border="0" var="record" value="#{report.expReport.records}">

                    <f:facet name="header">
                        <rich:columnGroup>

                            <rich:column rowspan="2">
                                <rich:spacer />
                            </rich:column>

                            <rich:column colspan="3">
                                <h:outputText value="Expenses" />
                            </rich:column>

                            <rich:column rowspan="2">
                                <h:outputText value="subtotals" />
                            </rich:column>

                            <rich:column breakBefore="true">
                                <h:outputText value="Meals" />
                            </rich:column>

                            <rich:column>
                                <h:outputText value="Hotels" />
                            </rich:column>
                            <rich:column>
                                <h:outputText value="Transport" />
                            </rich:column>
                        </rich:columnGroup>

                    </f:facet>
   

                    <rich:column  colspan="5">
                    <h:outputText value="#{record.city}" /></rich:column>

                    
                    <rich:subTable
                        onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
                        onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                        var="expense" value="#{record.items}">
                            
                        <rich:column>
                            <h:outputText value="#{expense.day}"></h:outputText>
                            <f:facet name="footer">
                                <rich:spacer />
                            </f:facet>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="#{expense.meals}"><f:convertNumber   pattern="$####.00"  /></h:outputText>
                            <f:facet name="footer">
                                <h:outputText value="#{record.totalMeals}"><f:convertNumber  pattern="$####.00" /></h:outputText>
                            </f:facet>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="#{expense.hotels}"><f:convertNumber  pattern="$####.00"  /></h:outputText>
                            <f:facet name="footer">
                                <h:outputText value="#{record.totalHotels}"><f:convertNumber   pattern="$####.00"  /></h:outputText>
                            </f:facet>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="#{expense.transport}"><f:convertNumber   pattern="$####.00"  /></h:outputText>
                            <f:facet name="footer">
                                <h:outputText value="#{record.totalTransport}"><f:convertNumber   pattern="$####.00"  /></h:outputText>
                            </f:facet>
                        </rich:column>
                        
                        <rich:column>
                            <rich:spacer></rich:spacer>
                            <f:facet name="footer">
                                <h:outputText value="#{record.total}"><f:convertNumber   pattern="$####.00"    /></h:outputText>
                            </f:facet>
                        </rich:column>                  
                    </rich:subTable>

                    <f:facet name="footer">
                        <rich:columnGroup>
                            <rich:column>Totals</rich:column>
                            <rich:column>
                                <h:outputText value="#{report.expReport.totalMeals}"><f:convertNumber   pattern="$####.00"  /></h:outputText>
                            </rich:column>
                            <rich:column>
                                <h:outputText value="#{report.expReport.totalHotels}"><f:convertNumber   pattern="$####.00"  /></h:outputText>
                            </rich:column>
                            <rich:column>
                                <h:outputText value="#{report.expReport.totalTransport}"><f:convertNumber   pattern="$####.00"  /></h:outputText>
                            </rich:column>
                            <rich:column>
                                <h:outputText value="#{report.expReport.grandTotal}"><f:convertNumber   pattern="$####.00"  /></h:outputText>
                            </rich:column>
                        </rich:columnGroup>
                    </f:facet>
                </rich:dataTable>


                --%>
                
                <%--  Data Grid --%>
    <style>
    .label{
    font-weight:bold;
    }
    </style>

    <rich:panel>
        <f:facet name="header">
            <h:outputText value="Car Store"></h:outputText>
        </f:facet>

        <h:form>
        <rich:dataGrid value="#{products.product2}" var="produto" columns="1" elements="10">
            <rich:panel style="width:450px">
                <f:facet name="header">
                    <rich:column colspan="2">
                        <h:outputText value="#{produto.descricao} #{produto.preco}"></h:outputText>
                    </rich:column>
                </f:facet>
                
                <h:panelGrid columns="4" style="width:450px">
                    <rich:column width="15px">
                        <h:outputText value="Price:" styleClass="label"></h:outputText>
                        <h:outputText value="#{produto.preco}" />
                        <rich:spacer width="1" height="5" />
                        <h:outputText value="#{produto.preco}">
                            <f:convertNumber  pattern="$####.00"  />
                        </h:outputText>
                    </rich:column>
                    <rich:column style="width:400px">
                        <h:outputText value="Mileage:" styleClass="label"></h:outputText>
                        <h:outputText value="#{produto.nome}" />
                    </rich:column>
                    <rich:column width="10px">
                        <h:outputText value="VIN:" styleClass="label"></h:outputText>
                        <h:outputText value="#{produto.descricao}" />
                    </rich:column>    
                    <rich:column width="10px">
                        <h:outputText value="Stock:" styleClass="label"></h:outputText>
                        <h:outputText value="#{produto.qtde}" />
                    </rich:column>
                </h:panelGrid>
            </rich:panel>
            <f:facet name="footer">
                <rich:datascroller></rich:datascroller>
            </f:facet>
        </rich:dataGrid>
        </h:form>
    </rich:panel>                       

        </table>
    </body>
</html>
