package uz.rootec.appvacineserver.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceTransformer;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;
import uz.rootec.appvacineserver.entity.Patient;
import uz.rootec.appvacineserver.repository.PatientRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class MainPageTransformer implements ResourceTransformer {

    @Autowired
    PatientRepository patientRepository;

//    //
//    public MainPageTransformer() {
//        this.courseRepository = (CourseRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("courseRepository");
//        this.roadmapRepository = (RoadmapRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("roadmapRepository");
//        this.bootcampRepository = (BootcampRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("bootcampRepository");
//
//    }


    @Override
    public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain) throws IOException {
        String url = request.getRequestURI();
        String html = IOUtils.toString(resource.getInputStream(), "UTF-8");
        if (url.contains("/sertificate")) {
            try {
                String urls[] = url.split("/");
                Patient patient = patientRepository.getOne(UUID.fromString(urls[2]));
//                String stringDate = patient.getGivenDate();
//                Date date1=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(stringDate);
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(date1);
//                cal.add(Calendar.HOUR_OF_DAY, 4);
//                Date time = cal.getTime();
//                DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//                String format = targetFormat.format(date1);
//                String format1 = targetFormat.format(time);
                html = html.replaceAll("<div id=\"root\"></div>", "<div style=\"background: transparent url(https://vaccination21.com/img/uzgerb.jpg) no-repeat; background-size: cover; background-position: center top; background-repeat: no-repeat;\" data-new-gr-c-s-check-loaded=\"14.1029.0\" data-gr-ext-installed=\"\" cz-shortcut-listen=\"true\">\n" +
                        "<div style=\"width: 977px !important;height: 1448px!important;font-family: Georgia, 'Times New Roman', Times, serif;border: 3px solid #444444;margin:15px auto !important\">\n" +
                        "    <table style=\"width:100% !important;  margin-bottom:30px !important;margin-top:15px !important;font-family: Georgia, 'Times New Roman', Times, serif;\">\n" +

                        "        <tbody><tr>\n" +
                        "            <td style=\"width:400px !important;font-size:18px !important;text-align:center !important;font-family: Georgia, 'Times New Roman', Times, serif;vertical-align: middle;font-weight:bold\">O'ZBEKISTON RESPUBLIKASI<br>SOG'LIQNI SAQLASH VAZIRLIGI<br>KORONAVIRUS (COVID-19) GA QARSHI<br>EMLANGANLIK TO‘G‘RISIDA<br>SERTIFIKAT</td>\n" +
                        "            <td style=\"width:180px !important;text-align:center !important\"><img src=\"/gerb.png\" style=\"width:160px\"></td>\n" +
                        "            <td style=\"width:400px !important;font-size:18px !important;text-align:center !important;font-family: Georgia, 'Times New Roman', Times, serif;vertical-align: middle;font-weight:bold\">MINISTRY OF HEALTH<br>OF THE REPUBLIC OF UZBEKISTAN <br>CORONAVIRUS (COVID-19)<br>VACCINATION<br>CERTIFICATE</td>\n" +
                        "        </tr>\n" +
                        "        </tbody></table>\n" +
                        "    <div style=\"width:100% !important;font-size:18px !important;padding: 10px!important;font-family: Georgia, 'Times New Roman', Times, serif;\">\n" +
                        "        <p><b>ID: "+ patient.getSerial() +"</b></p>\n" +
                        "        <p>Emlash punkti/Место вакцинации/Place of vaccination: <b>"+ patient.getVacinePlace() +"</b></p>\n" +
                        "        <p>Vaksina turi/Тип вакцины/Type of vaccine: <b>"+ patient.getVacineType() +"</b></p>\n" +
                        "\n" +
                        "    <div style=\"display: flex; align-items: center\">\n" +
                        "            <p style=\"white-space: nowrap; display: inline-block\">Seriya raqami/Номер серии/Serial number:</p>\n" +
                        "            <div style=\"margin-left: 52px; background:#ddd; padding:3px 10px; display: inline-block;\"><b>1</b></div>\n" +
                        "            <div style=\"border:1px solid #ddd; width: 145px; display: inline-block; padding: 2px;margin-left: -5px;\"><b>"+ patient.getVacineSerials().get(0) +"</b></div>\n" +
                        "            <div style=\"margin-left: 15px; background:#ddd; padding:3px 10px; display: inline-block;\"><b>2</b></div>\n" +
                        "            <div style=\"border:1px solid #ddd; width: 145px; display: inline-block; padding: 2px;margin-left: -5px;\"><b>"+ patient.getVacineSerials().get(1) +"</b></div>\n" +
                     
                        "\n" +
                        "        </div>\n" +
                        "        <div style=\"display: flex; align-items: center; margin-bottom: 1rem;\">\n" +
                        "            <p style=\"white-space: nowrap; display:inline-block;\">Emlash vaqti/Дата вакцинации/Vaccination date:</p>\n" +
                        "            <div style=\"background:#ddd; padding:3px 10px; display: inline-block; margin-left: 7px;\"><b>1</b></div>\n" +
                        "            <div style=\"border:1px solid #ddd; width: 145px; display: inline-block; padding: 2px;margin-left: -5px;\"><b>"+ patient.getVacineDates().get(0) +"</b></div>\n" +
                        "            <div style=\"margin-left: 15px; background:#ddd; padding:3px 10px; display: inline-block;\"><b>2</b></div>\n" +
                        "            <div style=\"border:1px solid #ddd; width: 145px; display: inline-block; padding: 2px;margin-left: -5px;\"><b>"+ patient.getVacineDates().get(1) +"</b></div>\n" +
                        "\n" +
                     
                        "        </div>" +
                        "        <p>Pasport seriya va raqami/Серия и номер паспорта/Passport serial number: <b>"+ patient.getPassportNumber() +"</b></p>\n" +
                        "        <p>JSHSHIR / ПИНФЛ / PINFL: <b>"+ patient.getPinfl() +"</b></p>\n" +
                        "        <p>To‘liq ismi/Полное имя/Full name: <b>"+ patient.getFullName() +"</b></p>\n" +
                        "\n" +
                        "        <p>Tug‘ilgan sana/Дата рождения/Date of birth: <b>"+ patient.getBirthDate() +"</b></p>\n" +
                        "        <p>Jinsi/Пол/Sex: <b>" + patient.getGender() + "</b></p>\n" +
                        "        <p>Berilgan sana/Дата выдачи/Date of issue: <b>" + patient.getGivenDate() +"</b></p><div style=\"text-align:center;margin-top:30px\"><img width=\"231\" height=\"231\" src=\"https://api.qrserver.com/v1/create-qr-code/?size=320x320&data="+ URLEncoder.encode("https://webtopdf.expeditedaddons.com/?api_key=53YH4OUM7G8Q9BJI4NVF6CW590X0SDZT7L1KEA8R3621P2&content=https://app-vacinee.herokuapp.com/sertificate/" + url.split("/")[2], "UTF-8")  +"\" style=\"border: 1px solid #ddd;\"></div>\n" +
                        "        <p style=\"text-align:center;line-height: 1.8;padding-top: 190px\">O‘zbekiston Respublikasi sanitariya-epidemiologik osoyishtalik va jamoat salomatligi xizmati<br>\n" +
                        "            Manzili: Toshkent shahar, Chilonzor tumani, Bunyodkor ko‘chasi, 45<br>\n" +
                        "            Telefon: +998 71 276 40 71<br>\n" +
                        "            Email: kancelyariyaresdsenm@ssv.uz</p>\n" +
                        "        <div style=\"text-align:center\"><img src=\"/footer.png\"></div>\n" +
                        "    </div> \n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "</div>");
            } catch (Exception e) {
                e.printStackTrace();
                return new TransformedResource(resource, html.getBytes());
            }
        }

        return new TransformedResource(resource, html.getBytes());
    }


}
