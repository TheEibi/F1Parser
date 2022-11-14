/**
 * 
 */
package formula.trackpos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import formula.data.DriverLapDistance;

/**
 * @author reinh
 *
 */
public class F1TrackPositionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<DriverLapDistance> dldList;
	private short safetyCarType = 0;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Float(getWidth() / 4f, 5, 20, 50));

		Color scColor = null;
		switch (safetyCarType) {
		case 1:
			scColor = Color.YELLOW;
			break;
		case 2:
			scColor = Color.CYAN;
			break;
		default:
			scColor  = Color.GRAY;

		}
		
		g2.setColor(scColor);
		g2.fill(new Rectangle2D.Float(0, 45, 1300, 5));


		if (dldList != null) {
			for (DriverLapDistance driverLapDistance : dldList) {

				float distance = driverLapDistance.getLapDistance();
				if (driverLapDistance.getLapDistance() > getWidth()) {
					distance = driverLapDistance.getLapDistance() - getWidth();
				}
				g2.setColor(driverLapDistance.getTeamColor());
				g2.fill(new Rectangle2D.Float(distance, 25, 20, 30));
			}
		}
	}


	public void setSafetyCarType(short argSafetyCarType) {
		safetyCarType = argSafetyCarType;
	}

	public void setDriverLapDistance(List<DriverLapDistance> argDriverLapDistance) {
		dldList = argDriverLapDistance;

	}

	public List<DriverLapDistance> getDriverLapDistance() {
		return dldList;
	}
}
