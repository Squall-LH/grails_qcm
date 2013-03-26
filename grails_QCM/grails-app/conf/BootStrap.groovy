import grails_qcm.*;
import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

    def init = { servletContext ->
		environments {
			development {
				QuizzService qServ = new QuizzService();
				qServ.fillDDB();
			}
			production {
				QuizzService qServ = new QuizzService();
				qServ.fillDDB();
			}
		}
    }
    def destroy = {
    }
}
