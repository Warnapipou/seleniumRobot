/*
 * Copyright 2015 www.seleniumtests.com
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seleniumtests.browserfactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.seleniumtests.core.SeleniumTestsContext;
import com.seleniumtests.core.TestLogging;

import com.seleniumtests.customFFprofile.FireFoxProfileMarker;

import com.seleniumtests.driver.DriverConfig;

import com.seleniumtests.helper.FileUtility;
import com.seleniumtests.helper.OSUtility;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

public class MarionetteCapabilitiesFactory extends FirefoxCapabilitiesFactory {

	public DesiredCapabilities createCapabilities(final DriverConfig webDriverConfig) {
		DesiredCapabilities capabilities = super.createCapabilities(webDriverConfig);
		capabilities.setCapability("marionette", true);
		try {
			configureGeckoDriver();
		} catch (UnsupportedEncodingException e) {
		}
		
		return capabilities;
		
	}
	
	private void configureGeckoDriver() throws UnsupportedEncodingException {
		String dir = Paths.get(SeleniumTestsContext.ROOT_PATH, "tools", "drivers", Platform.getCurrent().family().toString().toLowerCase()).toString();
        dir = FileUtility.decodePath(dir);

        if (OSUtility.isWindows()) {
            System.setProperty("webdriver.gecko.driver", dir + "\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", dir + "/geckodriver");
            new File(dir + "/geckodriver").setExecutable(true);
        }
	}
    
}