package org.eclipse.jetty.demo;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

import java.net.URL;

public class EmbedMe
{
    public static void main(String[] args) throws Exception
    {
        int port = 8080;
        Server server = new Server(port);

        URL webXml = EmbedMe.class.getResource("/WEB-INF/web.xml");
        WebAppContext context = new WebAppContext();
        context.setResourceBase(webXml.toURI().resolve("..").toString());
        context.setDescriptor(webXml.toString());
        context.setConfigurations(new Configuration[]
        { 
            new AnnotationConfiguration(), 
            new WebInfConfiguration(), 
            new WebXmlConfiguration(), 
            new MetaInfConfiguration(), 
            new FragmentConfiguration(),
            new EnvConfiguration(), 
            new PlusConfiguration(), 
            new JettyWebXmlConfiguration() 
        });

        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();
        server.dump(System.err);
        server.join();
    }
}
