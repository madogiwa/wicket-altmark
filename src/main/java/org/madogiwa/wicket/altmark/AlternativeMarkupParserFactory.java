/*
 * Copyright (c) 2010 Hidenori Sugiyama
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package org.madogiwa.wicket.altmark;

import org.apache.wicket.markup.MarkupParser;
import org.apache.wicket.markup.MarkupParserFactory;
import org.apache.wicket.markup.MarkupResourceStream;
import org.apache.wicket.markup.parser.XmlPullParser;

/**
 * @author Hidenori Sugiyama
 *
 */
public class AlternativeMarkupParserFactory extends MarkupParserFactory {

	private String prefix = "wicket-";

	private boolean prefixRemoving = true;

	/**
	 * 
	 */
	public AlternativeMarkupParserFactory() {
		
	}

	/**
	 * @param prefix
	 */
	public AlternativeMarkupParserFactory(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @param prefix
	 * @param prefixRemoving
	 */
	public AlternativeMarkupParserFactory(String prefix, boolean prefixRemoving) {
		this.prefix = prefix;
		this.prefixRemoving = prefixRemoving;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.MarkupParserFactory#newMarkupParser(org.apache.wicket.markup.MarkupResourceStream)
	 */
	@Override
	public MarkupParser newMarkupParser(MarkupResourceStream resource) {
		MarkupParser markupParser = new MarkupParser(new XmlPullParser(), resource);
		markupParser.appendMarkupFilter(new HtmlTagIdentifier(prefix, prefixRemoving));
		return markupParser;
	}

}
