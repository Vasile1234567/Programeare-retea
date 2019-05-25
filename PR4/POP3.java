import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class POP3 implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "";// mail send
        String password = "";// password

        check(host, mailStoreType, username, password);
    }
       private void check(String host, String storeType, String user, String password)
        {
            try {
                //create properties field
                Properties properties = new Properties();

                properties.put("mail.pop3.host", host);
                properties.put("mail.pop3.port", "995");
                properties.put("mail.pop3.starttls.enable", "true");
                Session emailSession = Session.getDefaultInstance(properties);

                //create the POP3 store object and connect with the pop server
                Store store = emailSession.getStore("pop3s");

                store.connect(host, user, password);

                //create the folder object and open it
                Folder emailFolder = store.getFolder("INBOX");
                emailFolder.open(Folder.READ_ONLY);

                // retrieve the messages from the folder in an array and print it
                Message[] messages = emailFolder.getMessages();
                System.out.println("Total messages is " + messages.length);
                for (int i = messages.length - 1, n = messages.length; i < n; i--) {
                    Message message = messages[i];
                    System.out.println("___________________________________________________________________________________");
                    System.out.println("Email Number " + (i + 1));
                    System.out.println("Subject: " + message.getSubject());
                    System.out.println("From: " + message.getFrom()[0]);
                    System.out.println("Body: " + message.getContent());
                    if (i == 249){
                        break;
                    }
                }
                //close the store and folder objects
                emailFolder.close(false);
                store.close();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}