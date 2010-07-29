<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
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
        <link href="resources/aquaStyle.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <h2>AquaSys</h2>
        <br />
        
        <f:view>
            <rich:comboBox suggestionValues="#{user.allBank}"
            directInputSuggestions="true" defaultLabel="Enter some value" 
            value="#{user.descricaoBank}"
            onselect="#{user.idBank}"          
            />

<rich:dataTable value="#{cart.resultPartList}" var="table" id="list"></rich:dataTable>
<h:panelGrid columns="2" style="margin-top: 3px" width="100%" border="10"></h:panelGrid >
            <rich:column >
            
            <h:inputText id="SRg" value="#{user.user.SRg}" title="#{msg['user.label.sRg']}" disabled="true" />
            
                       <h:selectOneRadio id="radio">
                                    <f:selectItem itemLabel="" itemValue="#{tableUser.idUser}" />
                                </h:selectOneRadio>            
            
            <h:commandLink  binding="false" id="confirmar" immediate="true" action="#{cart.info}" actionListener="#{cart.processAction}" ></h:commandLink>s

                                <rich:dataTable value="#{cart.resultList}" var="table" id="list"
                                    onRowMouseOver="this.style.backgroundColor='#F1F1F1'"
                                    onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
                                    cellpadding="0" cellspacing="0"  
                                    width="100%" border="0" rows="10"></rich:dataTable>
                
            <h:commandLink id="confirmar" immediate="true" actionListener="#{user.processAction}" >
                <f:param id="userId" name="id" value="#{tableUser.idUser}" />
                <h:selectOneRadio id="radio"  required="false">
                    <f:selectItem itemLabel="" itemValue="#{tableUser.idUser}" />
                </h:selectOneRadio>
            </h:commandLink>
            <a4j:region binding="false"></a4j:region>
                <rich:inputNumberSpinner label="lalal" value="50"/>
            
            <h:selectOneRadio id="radio" value=""/>
            <h:panelGrid style="with: 200px" width="">
            <h:inputSecret id="" value="#{login.loggedUser.SPassword}" size="20" maxlength="20" redisplay="false" required="true" />
            <rich:inplaceInput defaultLabel="click to enter your email" showControls="true" />
            <h:inputText id="id" value="#{products.product.idProduct}" size="20" required="true" accesskey="true"  />
            <h:outputText value="teste" />
            <rich:inplaceInputs defaultLabel="click to enter your email" showControls="true" />
            
            
            
            <rich:toolBar id="toolBar" width="100%" height="20" >
            <rich:dataGrid align="left"
            <h:outputLink onblur="true"
<h:outputLabel value="#{msg['product.label.id']}" for="id" />
<h:inputText id="id" value="#{products.product.idProduct}" size="20" disabled="true"/>            
            
            <h:outputText id="detail" value="#{products.product.sDescCurtaProd}" />
                <rich:dataTable value="#{products.resultList}" var="produtos">
                    <rich:column>
                        <h:outputText id="detail" value="#{produtos.sDescCurtaProd}" />
                    </rich:column>
                </rich:dataTable>            
            
                <rich:dataTable value="#{products.getResultList}" var="produtos">
                    <rich:column>
                        <h:outputText id="detail" value="#{produtos.description}" />
                    </rich:column>
                </rich:dataTable>
                
                <rich:comboBox value=""/>
            <a4j:form>
                <div id="geral">
                    <div id="cabecalho">
                        <h:outputText value="#{facesContext.externalContext.requestContextPath}" />
                        <rich:separator />
                        
                        <a4j:page>
                            <f:facet name="header">
                                <%-- <h:graphicImage value="  /CAMINHO/ " height="50"> --%>
                                <a4j:include viewId="/templates/page-header.jsp" /> 
                            </f:facet>
                        </a4j:page>
                    </div>
                    
                    <h:commandLink id="link-product" action="product-edit" value="product" actionListener=""/>
                    <h:commandButton id="link-product" action="product-edit" value="product-edit" actionListener="#{product.actionDeselect}" immediate="true"/>
                    <rich:separator height="5" />

                    <div id="esquerda">
                        <a4j:page></a4j:page>
                        
                        <%-- Link ?--%>
                        <h:panelGrid columns="1" width="100%" columnClasses="tpanels,tpanels,tpanels">
                            <rich:simpleTogglePanel switchType="ajax" label="menu" height="90">
                                <a4j:commandLink action="#{products.changeName1}" value="link" reRender="text" />
                                <br />
                                <a4j:commandLink id="cLink" action="#{products.changeName2}" value="Click it To send ajax" reRender="text" />
                            </rich:simpleTogglePanel>
                        </h:panelGrid>

                    </div>

                    <div id="direita">
                        <a4j:page>
                            <p>Direita</p>
                        </a4j:page>
                    </div>

                    <div id="conteudo">
                        <a4j:page>
                            <rich:panel header="teste">
                                Teste   
                                <h:panelGroup id="wizard">
                                    
                                    <a4j:include viewId="/pages/first.jsp" />
                                    
                                    
                                </h:panelGroup>
                            </rich:panel>
                        </a4j:page>
                        
                        <rich:tabPanel switchType="ajax">
                            <rich:tab label="First">
                                Tab 1
                            </rich:tab>
                            <rich:tab label="Second" disabled="true">
                                Tab 2
                            </rich:tab>
                        </rich:tabPanel>
                    </div>

                    <div id="divRodape">
                        <address>
                            <strong>RODAPE</strong>

                                <h:panelGroup id="rodape">
                                    <a4j:include viewId="/templates/page-footer.jsp" /> 
                                </h:panelGroup>
                            
                        </address>
                    </div>
                    
                    <rich:datascroller align="left" for="produtList" maxPages="20" 
                                       reRender="sc2" id="sc1"></rich:datascroller>
                    <rich:spacer width="30" />
                    
                        <div class="topo-dir">

                         <!-- Exemplo de box com sombra-->
                         <div class="baixo-esq">
                          <div class="baixo-dir">
                           <div class="texto">
                            <h3>Cabeçalho</h3>
                             <h:outputText id="text" value="#{products.name}" />
                           </div>
                          </div>
                         </div>
                        </div>

                </div>
            </a4j:form>
        </f:view>
    </body>
</html>
