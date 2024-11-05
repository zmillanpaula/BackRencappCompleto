package generation.rencapp.email;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class EmailService {
   @Value("${sendgrid.api.key}")
    private String apiKey;
   @Value("${sengrid.template.veterinaria}")
    private String templateVeterinaria;
   @Value("${sengrid.template.ornato}")
    private String templateOrnato;


    public void enviarCorreo(String destinatario, String asunto, String nombreDepartamento, Map<String, String> datos)  throws IOException {

        String template=(nombreDepartamento.equalsIgnoreCase("veterinaria")? templateVeterinaria:templateOrnato);


        Email remitente = new Email(" ");
        Email para = new Email(destinatario);

        Mail mail = new Mail();
        mail.setFrom(remitente);
        mail.setSubject(asunto);
        mail.setTemplateId(template);

        Personalization personalization = new Personalization();
        personalization.addTo(para);
        datos.forEach(personalization::addDynamicTemplateData);
        mail.addPersonalization(personalization);


        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}