#Dropwizard Technology Model

##1 The model

You can find our mega model in ass04 folder.

##2 Model description

###2.1 Key entities

The key entities in our model are the many technologys Dropwizard is bringing together in an easy and quick-to-use way, because this is what makes Dropwizard so powerful.<br>
Also important are various enviroments and configurations which are definately needed to get started with Dropwizard.

###2.2 Technology-specific insight we tried to model

We first tried to give the user an overview over all the technologys Dropwizard is providing and which so can be natively used in a Dropwizard project. Also we wanted to give a quick insight into all the abolutely necessary configuration, application and enviroment files to get Dropwizard running, because if this first "set-up" step is done, it's easy to pull out an awesome application by using the linked documentations of the provided technologys.

###2.3 Relationships between key entities

Between the used technologys there are no "external relationships"". You can use them all independentely in your ptoject. Internal they may be closely linked, because Dropwizard is depending on all of them, but that's not what we tried to model.<br>
There are interesting relationships between resources, enviroments and configurations.

1. The Jersey enviroment depends on the resource classes which are at least one Java file
2. The Jersey enviroment again is part of the Dropwizard Enviroment which of course is a part of Dropwizard
3. The Dropwizard configuration & application are Java files and are a part of Dropwizard
4. The Dropwizard Application depends on the Dropwizard configuration which depends on the configuration file which is a YAML file written in the YAML markup language

###2.4 New entity types

We modeled several entity subtypes, for example all the different kinds of technologys ("RestfulWebServicesFramework", "BeanValidator", ...) or "ArchStyle", a subtype of concept.

###2.5 New relationship types

We didn't need new realtionship types.