package states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import factories.StudentFactory;
import graphics.Assets;
import models.programmingLanguages.*;
import models.students.Student;
import models.wizards.Wizard;
import utils.Constants;
import core.CollisionHandler;
import core.MapInitializor;
import enums.StudentType;

public class GameState extends State {
	public static Student student;
    private List<ProgrammingLanguage> programmingLanguages = new ArrayList<>();
    private Wizard wizard;
    private long timerNewWizard = System.nanoTime();
	private long timerNewProgrammingLanguage = System.nanoTime();
	private long timeDelayNewWizard = 1000;
	private long timeDelayNewProgrammingLanguage = 500;

    StudentFactory studentFactory = new StudentFactory();

    public GameState(StudentType studentType, String name) {
		student = studentFactory.create(studentType, 500, 500, name);
		programmingLanguages.add(MapInitializor.generateProgrammingLanguage());
    	wizard = MapInitializor.generateWizard();
	}
    
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(Assets.wall, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, null);
        graphics.drawImage(Assets.floor, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, null);

        student.draw(graphics);
        
        if (!programmingLanguages.isEmpty()) {
        	 for (ProgrammingLanguage programmingLanguage : programmingLanguages) {
             	programmingLanguage.draw(graphics);
     		}
		}
       
       
        if (wizard != null) {
        	wizard.draw(graphics);
		}
        

    }

    @Override
    public void update() {
		
    	long elapsedNewWizard = (System.nanoTime() - this.timerNewWizard) / 1000000;
		long elapsedNewProgrammingLanguage = (System.nanoTime() - this.timerNewProgrammingLanguage) / 1000000;
    	
    	CollisionHandler.collisionHandler(student, wizard, programmingLanguages);
    	
        if (elapsedNewWizard > this.timeDelayNewWizard) {
			wizard = MapInitializor.generateWizard();
			timerNewWizard = System.nanoTime();
		}
    	
        if (elapsedNewProgrammingLanguage > this.timeDelayNewProgrammingLanguage) {
			programmingLanguages.add(MapInitializor.generateProgrammingLanguage());
			timerNewProgrammingLanguage = System.nanoTime();
		}
    	
        student.update();

    }
}
