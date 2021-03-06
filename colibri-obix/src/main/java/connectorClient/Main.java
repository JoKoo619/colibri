package connectorClient;

import channel.Connector;
import channel.colibri.ColibriChannel;
import channel.message.colibriMessage.ColibriMessage;
import channel.obix.ObixXmlChannelDecorator;
import exception.CoapException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Configurator;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * The main entry point for the obix connector.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        /*
            Load configuration from config.properties file. For example all OBIX Lobbies
         */
        Configurator configurator = Configurator.getInstance();
        List<Connector> connectors = configurator.getConnectors();
        boolean colibriIsRunning = false;
        boolean registerMessageSendt = false;

        /**
         * Start a connector for each obix channel.
         */
        for (Connector connector : connectors) {
            ColibriChannel colibriChannel = connector.getColibriChannel();
            connector.setObixChannel(new ObixXmlChannelDecorator(connector.getObixChannel()));
            try {
                if (!colibriIsRunning) {
                    colibriChannel.run();
                    colibriIsRunning = true;
                }
            } catch (IOException e) {
                logger.info(e.getMessage() + "\n" + "Please check the Colibri channel address in the config.properties file.");
                colibriChannel.close();
            }

            /**
             * Start the GUI.
             */
            final GuiUtility guiUtility = new GuiUtility(connector);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        guiUtility.runGui();
                    } catch (CoapException e) {
                        logger.info("Cannot connect to OBIX Lobby of host " + connector.getObixChannel().getBaseUri() +
                                " with the CoAP port " + connector.getObixChannel().getPort() + ". " +
                                "Maybe the lobby URI in the config.properties file is wrong, " +
                                "or the OBIX server is not online.");
                        guiUtility.close();
                    }
                }
            });
            if(!registerMessageSendt && connector.getObixChannel().getLobby() != null) {
                connector.getColibriChannel().send(ColibriMessage.createRegisterMessage(connector));
                registerMessageSendt = true;
            }
        }

        /**
         * Close all channels on stop through IDE.
         */
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for (Connector c : connectors) {
                    c.getColibriChannel().close();
                }
            }
        });

        /**
         * Close all channels on shutdown.
         */
        int connectorsRunning = 1;
        while (connectorsRunning > 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connectorsRunning = 0;
            for (Connector con : connectors) {
                if (con.isRunning()) {
                    connectorsRunning++;
                }
            }
            if (connectorsRunning == 0) {
                connectors.get(0).getColibriChannel().close();
            }
        }

    }
}
