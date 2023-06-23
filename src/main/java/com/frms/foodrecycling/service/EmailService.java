package com.frms.foodrecycling.service;

import com.frms.foodrecycling.entity.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//record foodItems(String item , String quantity){}

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendAssignmentMailToNGO(String toEmail, Assignment assignment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Assignment - Food Delivery for Member with request Id: #" + assignment.getRequestFood().getId().toUpperCase());

        String body = """  
                Dear %s,
                                
                Informing you about the assignment of food delivery for a specific member in need. The details of the assignment are as follows:
                                
                Member Name: %s
                Delivery Address: %s
                Delivery Date: %s
                Contact Number: %s
                
                Pick Up:
                Donor Name: %s
                Pickup Address: %s , %s
                Contact No: %s
                                
                This member is currently facing challenges and requires assistance with food supplies. We kindly request your organization's support in delivering essential food items to their location. Your dedication and efforts in helping those in need are greatly appreciated.
                                
                Please let us know if you require any further information or have any questions regarding this assignment. We trust in your commitment to making a positive impact on the community and are grateful for your collaboration.
                                
                Thank you for your ongoing support.
                                
                Best regards,
                                
                Administrator
                Food Recycling Team
                """.formatted(
                assignment.getNgo().getName().toUpperCase(),
                assignment.getRequestFood().getMember().getName(),
                assignment.getRequestFood().getAddress(),
                assignment.getDeliveryDate(),
                assignment.getRequestFood().getPhone()     ,
                assignment.getDonor().getName(),
                assignment.getDonor().getAddress(),
                assignment.getDonor().getCity(),
                assignment.getDonor().getPhone()
        );

        message.setText(body);
        mailSender.send(message);
    }

    public void sendConfirmationMailToMember(Assignment assignment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(assignment.getRequestFood().getMember().getEmail());
        message.setSubject("Confirmation - Food Delivery Assistance with request Id: #" + assignment.getRequestFood().getId().toUpperCase());

        String body = """    
                Dear %s,
                                
                We hope this email finds you well. We are pleased to inform you that %s, %s has accepted the responsibility of delivering essential food supplies to your location. Your request for assistance has been acknowledged, and our team is committed to ensuring that you receive the necessary support.
                                
                The details of the food delivery are as follows:
                                
                Delivery Date: %s
                Delivery Address: %s
                Contact Number: %s
                                
                We understand the importance of this service, especially during these challenging times, and our team will make every effort to ensure a smooth and timely delivery of the food items to your doorstep. We kindly request your cooperation in being available at the specified address during the delivery timeframe.
                                
                If you have any specific dietary requirements or preferences, please let us know in advance so that we can accommodate them to the best of our ability.
                                      
                We would like to express our gratitude for reaching out to us and allowing us to assist you during this time. If you have any further questions or need additional information, please do not hesitate to contact us. Your satisfaction and well-being are of utmost importance to us.
                                
                Thank you for placing your trust us. We look forward to serving you and making a positive impact together.
                                
                Warm regards,
                                
                Administrator
                Food Recycling Team
                """.formatted(
                assignment.getRequestFood().getMember().getName(),
                assignment.getNgo().getName().toUpperCase(),
                assignment.getNgo().getCity(),
                assignment.getDeliveryDate(),
                assignment.getRequestFood().getAddress(),
                assignment.getRequestFood().getPhone()
        );

        message.setText(body);
        mailSender.send(message);
    }
}
