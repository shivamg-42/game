package TileMap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap {
	
	// position
	private double x;
	private double y;
	
	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double tween;
	
	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	// drawing
	private int rowOffset; // which row to start drawing
	private int colOffset; // which column to start drawing
	private int numRowsToDraw; // how many to draw
	private int numColsToDraw;
	
	public TileMap (int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2; // two extra rows 
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		tween = 0.07;
	}
	
	public void loadTiles(String a) {
		
		try { 
			
			tileset = ImageIO.read(
					getClass().getResourceAsSteam(s) 
			);
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage( 
						col * tileSize,
						0,
						tileSize,
						tileSize
						);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(
						col * tileSize,
						tileSize,
						tileSize,
						tileSize
						);
				tiles[1][col] = new Tile (subimage, Tile.BLOCKED);
				
			}
			
		} // end try
			
		catch (Exception e) {
				e.printStackTrace();
			} // end catch
		
		} // end loadTiles
	}
	
	public void loadMap(String s) {
		
		try {
			
			InputStream in = getClass().getResourceAsStream(s);
			
			
		} // end try
		catch (Exception e) {
			e.printStackTrace();
		} // end catch
		
	} // end loadMap
	
}
