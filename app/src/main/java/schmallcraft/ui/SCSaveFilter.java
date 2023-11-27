package schmallcraft.ui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SCSaveFilter extends FileFilter {
	public static final String SCSAVE_EXTENSION = ".scsave";

	@Override
	public String getDescription() {
		return "SchmallCraft Save File";
	}

	@Override
	public boolean accept(File f) {
		return f.getName().endsWith(SCSAVE_EXTENSION) || f.isDirectory();
	}
}
