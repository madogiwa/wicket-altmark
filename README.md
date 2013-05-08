It's a simple library for Apache Wicket. You can use HTML ID attribute as a marker of wicket component.

##How to Use##

0.add JAR file into your classpath.

1.replace MarkupParserFactory to AlternativeMarkupParserFactory.

    // in Application class
    protected void init() {
        ...
        getMarkupSettings().setMarkupParserFactory(new AlternativeMarkupParserFactory());
        ...
    }

2.markup html template with HTML ID attribute.

    <!-- html template -->
    <span id="wicket-username"></span>

3.add component (ordinary way)

    // add Label component (remove "wicket-" from id)
    add(new Label("username", "Hoge Hoge"));

##repeatable component##

Do not use id attribute in repeater.

    <!-- HTML template -->
    <span id="wicket-username"></span> 
    ...

    <!-- output -->
    <span id="wicket-username"></span>
    <span id="wicket-username"></span>    <!-- HTML ID attribute must be unique. -->
    <span id="wicket-username"></span>
    ...

Instead, you can use HTML class attribute.

    <!-- html template (in repeater) -->
    <span class="wicket-username"></span>
