package com.bridgelabz.proxy;

public class CommandExecutorImpl implements CommandExecutor{

	public void runCommand(String cmd) throws Exception {
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec(cmd);
		System.out.println("'" + cmd + "' command executed.");
	}

}
