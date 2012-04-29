/* Copyright CNRS-CREATIS
 *
 * Rafael Silva
 * rafael.silva@creatis.insa-lyon.fr
 * http://www.rafaelsilva.com
 *
 * This software is a grid-enabled data-driven workflow manager and editor.
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */
package fr.insalyon.creatis.gasw.plugin.executor.dirac;

import fr.insalyon.creatis.gasw.GaswConfiguration;
import fr.insalyon.creatis.gasw.GaswException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 *
 * @author Rafael Silva
 */
public class DiracConfiguration {

    private static final Logger logger = Logger.getLogger("fr.insalyon.creatis.gasw");
    private static DiracConfiguration instance;
    private String host;
    private String defaultPool;
    private int defaultPriority;
    private String mysqlHost;
    private int mysqlPort;
    private String mysqlUser;
    private boolean notificationEnabled;
    private int notificationPort;
    private boolean balanceEnabled;

    public static DiracConfiguration getInstance() throws GaswException {

        if (instance == null) {
            instance = new DiracConfiguration();
        }
        return instance;
    }

    private DiracConfiguration() throws GaswException {

        try {
            PropertiesConfiguration config = GaswConfiguration.getInstance().getPropertiesConfiguration();

            host = config.getString(DiracConstants.LAB_HOST, "localhost");
            defaultPool = config.getString(DiracConstants.LAB_DEFAULT_POOL, "gLite");
            defaultPriority = config.getInt(DiracConstants.LAB_DEFAULT_PRIORITY, 1);
            mysqlHost = config.getString(DiracConstants.LAB_MYSQL_HOST, "localhost");
            mysqlPort = config.getInt(DiracConstants.LAB_MYSQL_PORT, 3306);
            mysqlUser = config.getString(DiracConstants.LAB_MYSQL_USER, "gasw");
            notificationEnabled = config.getBoolean(DiracConstants.LAB_NOTIFICATION_ENABLED, false);
            notificationPort = config.getInt(DiracConstants.LAB_NOTIFICATION_PORT, 50009);
            balanceEnabled = config.getBoolean(DiracConstants.LAB_BALANCE_ENABLED, false);

            config.setProperty(DiracConstants.LAB_HOST, host);
            config.setProperty(DiracConstants.LAB_DEFAULT_POOL, defaultPool);
            config.setProperty(DiracConstants.LAB_DEFAULT_PRIORITY, defaultPriority);
            config.setProperty(DiracConstants.LAB_MYSQL_HOST, mysqlHost);
            config.setProperty(DiracConstants.LAB_MYSQL_PORT, mysqlPort);
            config.setProperty(DiracConstants.LAB_MYSQL_USER, mysqlUser);
            config.setProperty(DiracConstants.LAB_NOTIFICATION_ENABLED, notificationEnabled);
            config.setProperty(DiracConstants.LAB_NOTIFICATION_PORT, notificationPort);
            config.setProperty(DiracConstants.LAB_BALANCE_ENABLED, balanceEnabled);

            config.save();

        } catch (ConfigurationException ex) {
            logger.error(ex);
        }
    }

    public boolean isBalanceEnabled() {
        return balanceEnabled;
    }

    public String getDefaultPool() {
        return defaultPool;
    }

    public int getDefaultPriority() {
        return defaultPriority;
    }

    public String getHost() {
        return host;
    }

    public String getMysqlHost() {
        return mysqlHost;
    }

    public int getMysqlPort() {
        return mysqlPort;
    }

    public String getMysqlUser() {
        return mysqlUser;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public int getNotificationPort() {
        return notificationPort;
    }
}