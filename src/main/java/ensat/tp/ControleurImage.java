/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ensat.tp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Controleur", urlPatterns = {"/ControleurImage"})
public class ControleurImage extends HttpServlet {

    
    

	private static final long serialVersionUID = 1L;

	protected void AfficherTexte(HttpServletRequest request, HttpServletResponse response, String texte, 
            int nb) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Afficher Texte</title>");            
            out.println("</head>");
            out.println("<body>");
            for(int i= 1; i<nb ; i++){
            out.println("<h3>"+ texte + "</h3>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void AfficherImage(HttpServletRequest request, HttpServletResponse response, String texte,
            int nb) throws ServletException, IOException{
        int size= 1000; //Déclare une variable size avec une valeur de 1000 pixels. 
        BufferedImage im = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB); //Crée une instance de BufferedImage avec la taille spécifiée (size x size)
        //le type de couleur BufferedImage.TYPE_INT_RGB. 
        // Cela crée une image RGB où chaque pixel est représenté par une valeur entière correspondant à la couleur R G B.
        Graphics2D g = im.createGraphics(); //Obtient un objet Graphics2D associé à l'image BufferedImage créée précédemment. 
        //Graphics2D est utilisé pour dessiner des formes et du texte sur l'image.
        g.setColor(Color.red);
        g.fillRect(0, 0, size, size); //dessine un rectangle de la taille de l'image rempli avec la couleur rouge
        //g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        for(int i= 1; i<nb ; i++){
            Graphics2D g1 = (Graphics2D) g.create(); // en créer une copie pour ne pas altèrer 
            //les caractéristiques fixées précédemment
            
            g1.setColor(Color.yellow);
            g1.setFont(new Font("Times New Roman", Font.PLAIN, 50));
            //Cette ligne définirait la police de caractères utilisée pour dessiner du texte sur l'image. Dans ce cas, la police "Times New Roman" 
            //avec une taille de police de 50 points est utilisée, sans style supplémentaire.
            //Font.PLAIN  sans appliquer de style 
            //sinon Font.BOLD  Font.ITALIC
            g1.drawString(texte,size/2+20 , size/2); //size/2 + 20 sur l'axe X et size/2 sur l'axe Y.
            g1.rotate(i*(2*Math.PI)/nb, size/2, size/2); //La rotation est effectuée autour du point (size/2, size/2), 
            
            //2π (2 pi) correspond à la longueur de l'arc complet d'un cercle dans le système d'angles radiaux. 
            //En d'autres termes, c'est l'angle en radians qui représente un tour complet (360 degrés) autour du centre d'un cercle.





            //qui semble être le centre de l'image.
            g1.drawString(texte, size/2+20, size/2);
        }
         ServletOutputStream out =   response.getOutputStream();
         ImageIO.write(im, "png" , out); //ImageIO.write(im, "png" , out);: Cette ligne écrit l'image (im) au format PNG dans le flux de sortie (out),
         //ce qui envoie finalement l'image générée au client qui a fait la requête HTTP.
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String texte = request.getParameter("text");
        int nb = Integer.valueOf(request.getParameter("nb"));
       String choix = request.getParameter("choix");
        
      if(choix.equals("HTML")){
          AfficherTexte(request, response, texte, nb); 
        }
       else{
          AfficherImage(request, response, texte, nb);
            
       }
    }

    
}
