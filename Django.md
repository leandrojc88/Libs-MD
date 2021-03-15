# Django

modelo MVC - en Django es **MTV**

![1594513762682](django\mtv.png)

## instalar

**online**

`pip install Django==3.0.2` 

**offline**

1. instalar dependencias 

   asgiref **falta el 3.2 descargarlo**->`pip install asgiref-1.1.1-py2.py3-none-any.whl`   

   pytz -> `pip install pytz-2019.3-py2.py3-none-any.whl`

   sqlparse -> `pip install sqlparse-0.3.0-py2.py3-none-any.whl`

2. instalar django `pip install django django-3.0.2.zip`

## Instalar local vs Virtual

![1594514520835](django\local vs virtual.png)

## Proyecto

### primer proyecto

1. `django-admin startproject Proyecto1` 
2. cd Projecto1
3. `python manage.py migrate` crea la bd.sqlite3
4. `python manage.py runserver` iniciar el servidor desarrollo

### estructura

:file_folder: **Proyecto1**
	:file_cabinet: **mange.py** - utilidades de líneas de comandos - `python manage.py help` - lista de comando
	:file_folder: **Proyecto1**
		:file_cabinet: **`__init__.py`** - para que python trate como un paquete **Proyecto1**
		:file_cabinet: **settings.py** - contiene las configuraciones del proyecto Django
		:file_cabinet: **urls.py** - almacena las url del Proyecto
		:file_cabinet:**wsgi.py** - información sobre el despliegue en producción 

:file_cabinet: **mange.py** -  `python manage.py help`

```shell
Available subcommands:

[auth]
    changepassword
    createsuperuser

[contenttypes]
    remove_stale_contenttypes

[django]
    check
    compilemessages
    createcachetable
    dbshell
    diffsettings
    dumpdata
    flush
    inspectdb
    loaddata
    makemessages
    makemigrations
    migrate
    sendtestemail
    shell
    showmigrations
    sqlflush
    sqlmigrate
    sqlsequencereset
    squashmigrations
    startapp
    startproject
    test
    testserver

[sessions]
    clearsessions

[staticfiles]
    collectstatic
    findstatic
    runserver
```



:file_cabinet: **settings.py** 

- `DEBUG = true` - permite estar en modo desarrollo, desactivar en Produccion

- `LANGUAGE_CODE = 'en-us'` - define el idioma por defecto `en-us`  - poner `es`(español)

- `DATABASES = {...}` - configuración para las bases de datos

  ```python
  DATABASES = {
      'default': {
          'ENGINE': 'django.db.backends.sqlite3',
          'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),
      }
  }
  ```

- `INSTALLED_APPS=[]` - contiene las aplicaciones instalas por Django inicialmente  

```python
INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles']
```

- `TEMPLATES['context_processors']` - contextos globales de l proyecto, estos valores son los que **django** inyecta dentro de las plantillas

  ```python
  'context_processors': [
       'django.template.context_processors.debug',
     'django.template.context_processors.request',
       'django.contrib.auth.context_processors.auth',
       'django.contrib.messages.context_processors.messages',
       'social.processors.ctx_def', # incluir aqui (metodo)
   ],
  ```

### contextos de Django

uso de contextos de django `TEMPLATES['context_processors']`, ejemplo del uso para editar un modelo si el usuario esta autenticado 

`'admin:pages_pages_change'` -> la url para -> `admin:App_Model_Action`

```python
{% if user.is_authenticated %}
	<p><a href="{% url 'admin:pages_pages_change' page.id %}">Editar</a></p>
{% endif %}
```



### procesador de contextos

definir función que retorne un diccionario con el contexto a incluir de forma global

```python
from .models import Link

def ctx_def(req):
    ctx = {}
    links = Link.objects.all()
    for link in links:
        ctx[link.key] = link.url
    return ctx
```

imcluirlo en **setings.py** 

```python
'context_processors': [
     'django.template.context_processors.debug',
     'django.template.context_processors.request',
     'django.contrib.auth.context_processors.auth',
     'django.contrib.messages.context_processors.messages',
     'social.processors.ctx_def', # incluir aqui (metodo)
 ],
```

## View (Vistas) 

### FBV : Function Base Views

1. crear fichero :file_cabinet: views.py , en el contenido del mismo define una función que recibe el parámetro `Httprequest` y retorna un `HttpResponse` 

   ```python
   from django.http import HttpResponse
   
   def saludo(request): # primera vista
       return HttpResponse("Hola mundo")
   ```

2. crear la url para la view en :file_cabinet: url.py 

   ```python
   from Proyecto1.views import saludo
   
   urlpatterns = [
       path('admin/', admin.site.urls),
       path('saludo', saludo),
       path('', views.home, name='home'),
   ]
   ```
### Parámetros en Urls

   utiliza url frendly evita ? y otros

   **:file_cabinet: Url.py**

   ```python
   path('calcular_edad/<int:edad>/<int:anno>/', calcular_edad),
   ```

   **:file_cabinet: views.py**

   ```python
   def calcular_edad(req, anno, edad):
       
       perido = anno - 2020
       edad_futura = edad + perido
       document = "<h2> en el año %s tendras %s "%(anno, edad_futura)
       return res(document)
   ```

### Urls formato Tupla (busqueda reversa)

se llama en los temples con *generic_name:name_path* `pages:crete`

**/app/urls.py** se crea la urls en forma de tupla con el segundo campo como el nombre genrico de la url

```python
from django.urls import path
from .views import PagesViews, PageDetailView, CreateView

pages_pattern = ([
    path('', PagesViews.as_view(), name='pages'),
    path('<int:pk>/<slug:page_slug>/', PageDetailView.as_view(), name='page'),
    path('create/', CreateView.as_view(),'create')
], 'pages')
```

**/urls.py** se importa y se pasa al include()

```python
from pages.urls import pages_pattern

urlpatterns = [
    path('',include('core.urls')),
    path('pages/',include(pages_pattern)),
    path('admin/', admin.site.urls),
]
```

### CBV: CLass Base Views

permiten todas las bondades de las clases propiedades, metodos, herencia etc

1. crear en el directorio **/views.py** las vistas como clases que heredan de `TemplateView`

   ```python
   from django.views.generic.base import TemplateView
   
   class HomePageView(TemplateView):
   
       template_name= "core/home.html"
   
   class SamplePageView(TemplateView):
       template_name= "core/sample.html"
   ```

2. llamar a las clases en **/urls.py** con el metodo `as_view()`

   ```python
   from django.urls import path
   from .views import HomePageView, SamplePageView
   
   urlpatterns = [
       path('', HomePageView.as_view(), name="home"),
       path('sample/', SamplePageView.as_view(), name="sample"),
   ]
   ```

- metodo `get()` redefine la respuesta de la **CBV ** en el cual con la función render se puede redefinir la respuesta

  ```python
  def get(self, request, *args, **kwargs):
          return render(request, self.template_name, {'title': 'titulo desde el get()'})
  ```

- utilizar **ListView** y **DetailView** se puede hacer referencia al modelo de estas vistas en el template con el propio nombre del modelo en minuscula o con `object` `object_list` 

  **/views.py**

  ```python
  from django.views.generic import ListView, DetailView
  from .models import Page
  
  # Create your views here.
  class PagesViews(ListView):
      model = Page
      template_name = "pages/pages.html"    
  
  class PageDetailView(DetailView):
      model = Page
  ```

  **/urls.py**

  ```python
  from django.urls import path
  from .views import PagesViews, PageDetailView
  
  urlpatterns = [
      path('', PagesViews.as_view(), name='pages'),
      path('<int:pk>/<slug:page_slug>/', PageDetailView.as_view(), name='page'),
  ]
  ```

### CBV: CRUD

**/views.py** - se crean las vistas que heredan de las **CreateView, UpdateView, DeleteView** se le pasan los modelos, Formularios y las success_url. espera los templates: ***page_confirm_delete.html, page_detail.html, page_form.html, page_update_form.html*** dependiendo de la acción del CRUD

```python
from django.views.generic.list import ListView
from django.views.generic.detail import DetailView
from django.views.generic.edit import CreateView, UpdateView, DeleteView
from .models import Page
from django.urls import reverse, reverse_lazy
from .forms import PageForm
# Create your views here.

class PagesViews(ListView):
    model = Page
    template_name = "pages/pages.html"


class PageDetailView(DetailView):
    model = Page


class PageCreate(CreateView):
    model = Page   
    form_class = PageForm    
    success_url = reverse_lazy('pages:pages')

class PageUpdate(UpdateView):
    model = Page   
    form_class = PageForm
    template_name_suffix = '_update_form'

    def get_success_url(self):
        return reverse_lazy('pages:update', args=[self.object.id]) + '?ok'

class PageDelete(DeleteView):
    model = Page
    success_url = reverse_lazy('pages:pages')
```

**/urls.py** - crean las urls para las vistas 

```python
from django.urls import path
from .views import PagesViews, PageDetailView, PageCreate, PageUpdate, PageDelete

pages_pattern = ([    
    path('create/', PageCreate.as_view(),name='create'),
    path('update/<int:pk>/', PageUpdate.as_view(),name='update'),
    path('delete/<int:pk>/', PageDelete.as_view(),name='delete'),
], 'pages' )
```

**/forms.py** - formularios con las configuraciones

```python
from django import forms
from .models import Page

class PageForm(forms.ModelForm):
    class Meta:
        model = Page
        fields = ['title', 'content', 'order']
        widgets = {
            'title': forms.TextInput(attrs={'class':'form-control',}),
            'content': forms.Textarea(attrs={'class':'form-control'}),
            'order': forms.NumberInput(attrs={'class':'form-control'}),
        }        
        labels = {
            'title': "",
            'content': '',
            'order': '',
        }
```

### mixins

clases que pueden ser reutilizadas con prioridad

```python
class StaffRequiredMixin():
    """ requerir que el usuario sea parte del estaff """

    def dispatch(self, request, *args, **kwargs):
        if not request.user.is_staff:
            return redirect(reverse_lazy('admin:login'))
        return super(StaffRequiredMixin, self).dispatch(request, *args, **kwargs)
    
class PageCreate(StaffRequiredMixin, CreateView):
    model = Page
    form_class = PageForm
    success_url = reverse_lazy('pages:pages') 

class PageUpdate(StaffRequiredMixin, UpdateView):
    model = Page
    form_class = PageForm
    template_name_suffix = '_update_form'

class PageDelete(StaffRequiredMixin, DeleteView):
    model = Page
    success_url = reverse_lazy('pages:pages')
```

### Decoradores

permite decorar los métodos con **method_decorator** de django, pasandole el `method_decorator(decorator, name='method')` para proporcionar algunas funcionalidades definidas en metodos decoradores como **staff_member_required, *login_required y permission_required** 

```python
from django.contrib.admin.views.decorators import staff_member_required
from django.utils.decorators import method_decorator

@method_decorator(staff_member_required, name='dispatch')
class PageCreate(CreateView):
    model = Page
    form_class = PageForm
    success_url = reverse_lazy('pages:pages') 
```

### Autenticación Personalizada

1. crear el path para la autenticacion `  path('accounts/', include('django.contrib.auth.urls'))`

   ```python
   urlpatterns = [
       path('admin/', admin.site.urls),
       # Path auth
       path('accounts/', include('django.contrib.auth.urls'))
   ]
   ```

2.  http://127.0.0.1:8000/accounts/ muestra el listado de urls, se crea los formularios automaticamente para cada urls, solo es cuestion de crear y editar el template.html

3. crear los **template.html** para cada url ej: `registration\templates\registration\login.html` para el login  http://127.0.0.1:8000/accounts/login/ . importante los <imput> deben tener la propiedad `name='username' y name='password'` para que funcione correctamente

   ```html
   <form action="" method="post">
                       {% csrf_token %}
      <input type="text" name="username" id="id_username" class="form-control" placeholder="Email address" required autofocus> 
       <input type="password" name="password" id="id_password" class="form-control" placeholder="Password" required>  
       <input class="btn btn-primary btn-block" type="submit"></input>                    
   </form>
   ```

   

4. la app **registration** de estar al inicio del **/setting.py** `INSTALED_APP=[]` 

5. para re direccionar el registro **/setting.py** al final `LOGIN_REDIRECT_URL = 'home'` -> busca un path con el nombre indicado ('home')

6. ***login y logout*** : re direccionar logout `LOGOUT_REDIRECT_URL = 'home'` 

   en el ejemplo se crean los vínculos para inicia y cerrar cesión 

   ```python
   <ul class="navbar-nav">
       {% if not request.user.is_authenticated %}
           <li class="nav-item">
           	<a class="nav-link" href="{% url 'login' %}">Acceder</a>
           </li>
       {% else %}
           <li class="nav-item">
           	<a class="nav-link" href="{% url 'logout' %}">Salir</a>
           </li>
       {% endif %}
   </ul>
   ```

### Registrarse

1. importar en el path las urls de regitration: `path('accounts/', include('registration.urls'))`

2. crear la vista que herede de CreateView y utilice el formulario UserCreationForm en **/views.py**

   ```python
   from django.contrib.auth.forms import UserCreationForm
   from django.views.generic import CreateView
   from django.urls import reverse_lazy
   
   class SignUpView(CreateView):
       form_class = UserCreationForm
       template_name = 'registration/signup.html'
   
       def get_success_url(self):
           return reverse_lazy('login') + '?register'
   ```

3. crear el tempalte.html de registro **/signup.html**

   ```python
   {% extends 'core/base.html' %}
   {% load static %}
   {% block title %}Borrar página{% endblock %}
   {% block content %}
   <main role="main">
       <div class="container">
           <div class="row mt-3">
               <div class="col-md-9 mx-auto mb-5">
                   <form action="" method="post">
                       {% csrf_token %}                    
                       <h1 class="h3 mb-3 font-weight-normal">Registro</h1> 
                           {{ form.as_p }}
                       <input class="btn btn-primary btn-block" type="submit" calur="Confirmar"></input>
                   </form>
               </div>
           </div>
       </div>
   </main>
   {% endblock %}
   ```

4. crear **/urls.py**

   ```python
   from django.urls import path
   from .views import SignUpView
   
   urlpatterns = [
       path('signup/', SignUpView.as_view(),name='signup'),
   ]
   ```

5. redefinir atributos (widget, labels) en tiempo de ejecución. **/views.py**

   ```python
   from django.contrib.auth.forms import UserCreationForm
   from django.views.generic import CreateView
   from django.urls import reverse_lazy
   from django import forms
   
   class SignUpView(CreateView):
       form_class = UserCreationForm
       template_name = 'registration/signup.html'
   
       def get_success_url(self):
           return reverse_lazy('login') + '?register'
       
       def get_form(self, form_class=None):
           form = super(SignUpView, self).get_form()
   
           # modificar el estilo en tiempo real
           form.fields['username'].widget = forms.TextInput(attrs={'class': 'form-control mb-2', 'placeholder':'Nombre Usuario'})
           form.fields['password1'].widget = forms.PasswordInput(attrs={'class': 'form-control mb-2', 'placeholder':'Contraseña'})
           form.fields['password2'].widget = forms.PasswordInput(attrs={'class': 'form-control mb-2', 'placeholder':'repetir contraseña'})
           # en el .html utilizar -> <style>label{display: none;}</style>
           form.fields['password2'].label = ''
   
           return form
   ```

6. redefinir **UserCreationForm** en **/forms.py** 

   ```python
   from django.contrib.auth.forms import UserCreationForm
   from django.contrib.auth.models import User
   from django import forms
   
   class UserEmailCreationForm(UserCreationForm):
       email = forms.EmailField(required=True, help_text="campo de 254 caracteres, formato user@email.com")
   
       class Meta:
           model = User
           fields = ['username', 'email', 'password1', 'password2']
       
       # validar correo unico   
       def clean_email(self):
           email = self.cleaned_data.get("email")
           if User.objects.filter(email=email).exists():
               raise forms.ValidationError("El correo ya esta en uso")
           return email
   ```

### resetear contraseña

1. crear la configuracion en **/settings.py** 

   ```python
   #emails - confururacion del correo de pruebas
   if DEBUG:
       EMAIL_BACKEND = "django.core.mail.backends.filebased.EmailBackend"
       EMAIL_FILE_PATH = os.path.join(BASE_DIR, 'sent_emails')
   else:
       # aqui configurar el email real
       pass
   ```

2.  http://127.0.0.1:8000/accounts/password_reset/ muestra el formulario de restauracion de contraseña, debemos implementar los tempales **password_reset_complete.html, password_reset_confirm.html, password_reset_done.html, password_reset_form.html** para personalizar las restauraciones de contraseñas

3.  link para recuperar pass `<a href="{% url 'password_reset' %}">click aqui para recuperarla...</a>` 

### Perfil de user

1. crear el modelo **/models.py**

   ```python
   from django.db import models
   from django.contrib.auth.models import User
   
   class Porfile(models.Model):
       user = models.OneToOneField(User, on_delete = models.CASCADE)
       avatar = models.ImageField(upload_to="porfiles", null=True, blank=True)
       bio = models.TextField(null=True, blank=True)
       link = models.URLField(max_length=200, null=True, blank=True)
   ```

2. **/views.py** - crear la clase que extienda de TemplateView con el nombre del template

   ```python
   from django.views.generic.base import TemplateView
   from django.contrib.auth.decorators import login_required
   from django.utils.decorators import method_decorator
   
   @method_decorator(login_required, name='dispatch')
   class PorfileUser(UpdateView):
       model = Porfile
       fields = ['avatar', 'bio', 'link']
       success_url = reverse_lazy('profile')
       template_name = 'registration/porfile_form.html'
   
       def get_object(self):
           # recuperar el objeto q se va a editar
           profile, created = Porfile.objects.get_or_create(user = self.request.user)
           return profile
   ```

3. **/urls.py** - asignar la url para la clase

   ```python
   from django.urls import path
   from .views import SignUpView, PorfileUser
   
   urlpatterns = [
       path('profile/', PorfileUser.as_view(),name='profile'),
   ]
   ```

4. definir el template.html  **/porfile_form.tml** 

5. eliminar la configuracion en **/settings.py** `# LOGIN_REDIRECT_URL = 'home'`

6. crear el link para la pagina de perfil `<a class="nav-link" href="{% url 'profile' %}">Perfil</a>`

7. migrar el modelo

    `py manage.py makemigrations registration` y `py manage.py migrate registration`

8. mejorando la UI del formulario de profile

### Cambiar contraseña

utilizar la **url**  `<a href="{% url 'password_change' %}"` 

y personalizar las plantillas **password_change_done.html y password_change_form.html**

### Señales (Hooks para ORM)

> es un disparador que se ejecuta automáticamente después de un evento en el ORM

se utiliza el decorador ***@receiver(evento_orm, sender=Model)*** 

***kwargs*** contiene la clave 'created' cuando es creado por primera ves

en este ejemplo se escucha después que un usuario es creado y se crea su perfil automáticamente

```python
from django.db import models
from django.contrib.auth.models import User
from django.db.models.signals import post_save
from django.dispatch import receiver

class Porfile(models.Model):
    user = models.OneToOneField(User, on_delete = models.CASCADE)
    avatar = models.ImageField(upload_to="porfiles", null=True, blank=True)
    bio = models.TextField(null=True, blank=True)
    link = models.URLField(max_length=200, null=True, blank=True)

@receiver(post_save, sender=User)
def ensure_profile_exists(sender, instance, **kwargs):
    if kwargs.get('created', False):
        Porfile.objects.get_or_create(user=instance)
        print('portafolio creado para el usuario')
```

### Prueba unitaria 

1. editar el ficheo **text.py**

2. crear la clase ClassnameTestCase(TestCase)

3. redefinir los dos métodos de la clase ***setUp(self):*** y ***test_method_name(self):*** este ultimo solo debe comenzar con **test_** el cual define la prueba

4. para ejecutar la prueba correr el comando `py manage.py test appname`

   **text.py**

```python
from django.test import TestCase
from .models import Porfile
from django.contrib.auth.models import User

# Create your tests here.

class ProfileTestCase(TestCase):
    def setUp(self):
        User.objects.create(username='test', email='test@test.com', password='test123')
        
    def test_profile_exist(self):
        exist = Porfile.objects.filter(user__username='test').exists()
        self.assertEqual(exist, True)
```

### Pagination

1. en la view se asigna la propiedad a la clase **paginate_by=2** con la cantidad de la paginación
2. crear en el template un paginador utilizando el objeto `page_obj.has_previous` `paginator` 
3. finalmente en el modelo definir la clase Meta con el atributo `ordering = ['file_orden']`

```python
# Create your views here.
class ProfileListView(ListView):
    model = Profile
    template_name = 'profiles/profile_list.html'
    paginate_by=2


# crear en el template un paginado

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
    {% if page_obj.has_previous %}
    <li class="page-item">
    <a class="page-link" href="?page={{ page_obj.previous_page_number }}" aria-label="Previous">&laquo;</a>
    </li>
    {% else %}
    <li class="page-item disabled">
    <a class="page-link " href="#" aria-label="Previous">&laquo;</a>
    </li>
    {% endif %}
    {% for i in paginator.page_range %}
    <li class="page-item {% if page_obj.numbre == i %}active{% endif %}">
    <a class="page-link" href="?page={{i}}">{{i}}</a>
    </li>
    {% endfor %}
    {% if page_obj.has_next %}
    <li class="page-item">
    <a class="page-link" href="?page={{ page_obj.next_page_number }}" aria-label="Next">&raquo;</a>
    </li>
    {% else %}
    <li class="page-item disabled">
    <a class="page-link " href="#" aria-label="Next">&raquo;</a>
    </li>
    {% endif %}              
    </ul>
 </nav>

# modelo definir la clase Meta con el atributo
class Profile(models.Model):
    user = models.OneToOneField(User, on_delete = models.CASCADE)
    
    class Meta:
        ordering = ['user']

```



## Template(Plantillas)

cadenas de textos (HTML casi siempre), separa la parte lógica de los datos de la visual (presentación)

1. Crear objeto de tipo Template `tpl = Template(doc_externo.read())`
2. Crear el contexto, para los datos adicionales `ctx = Context()`
3. Renderizado de plantilla `document = tpl.render(ctx)`

### variables y propiedades 

se le pasan al contexto en formato de diccionario

`ctx = Context({"nombre": "Leo", "Edad": 32, "objeto_nuevo": objeto, "lista":[1, 2]})`

**HTML**

```html
<body>
    <h1>
        Hola soy {{nombre}} tengo {{edad}}
    </h1>
    <p> {{objeto.campo}}</p>
    <p> {{lista.1}}</p>
</body>
```

### jerarquía de llamada

cuando se encuentra una nomenclatura de (.) cual es el orden a seguir, todos se llamas de = forma (métodos, atributos, etc.)

1. Diccionario
2. Atributo
3. Método
4. Índice de Lista

### bucles y condicionales

`{% nombre_bucles %}`

`{% end_nombre_bucles %}`

**for**

```python
{% for item in lista %}
	{{item}}
{% endfor %}
```

**if**

```python
{% if lista %}
	dentro
{% else %}
	no se muestra nada
{% endif %}
```

**operadore** son los mismo que en python (== , != >= , etc)

**comentarios**

{# comentario de 1 lienea #}

{% comment %} comentario de 

multiples lineas

{% endcomment %}

**filtros** - template filters

se llaman poniendo el (|)

```python
<p> {{nombre|lower}} </p> #pone en mayuscyla
<p> {{nombre|first}} </p> #solo primera letra
<p> {{nombre|lower|first}} </p> #pone en mayuscyla la primera letra
```

### template tag url

utiliza el nombre de la url en lugar del path

```python
path('/home', views.home, name='home')

<li><a href="{% url 'home' %}">home</a></li>
```

### template tags personalizados

1. crear el directorio **/templatetags/** en la app como un paquete python, crear `__init__.py` 

2. crear el fichero .py y en el contenido se registra el templatetag, con la función decoradora

   ```python
   from django import template
   from pages.models import Pages
   
   register = template.Library()
   
   @register.simple_tag # decorador que registra el templatetags
   def get_page_list():
       pages = Pages.objects.all()
       return pages
   ```

3. cargar el templatetags con el crgador `{% load %}` y tratarlo con un modificador `as` para registrar la variable q contenga los datos del templatetags

   ```python
   {% load pages_extra %}
   {% get_page_list as list_page %}
   {% for page in list_page %}
   <a href="{% url 'page' page.id %}" class="link"> {{ page.title }} </a>
   {% endfor %}
   ```

### statics

django busca todos los directorios statics y templates y los agrupa en un directorio genreal respectivamente **/tempate/core/** y **/statics/core/** dentro de esto definir todos los contenidos de la App

static - para los recursos, utilizar `{% load static%}` para decir que se van a cargar los recursos

`href= "{% static 'core/bootstrap.css' %}"` ejemplo para la url de los recursos

### cargadores plantillas

especificar el directorio donde están las plantillas

se espesifica en **settings.py** `TEMPLATES = [{'DIRS':['C:/plnatillas']}]`

al Método render() no se le pasa un Template sino un diccionario directamente, cuando se usan cargadores de plantillas porque son clases diferentes 

- ***usando django.shortcuts***  

```python
from django.shortcuts import render
def nosveremos(request): # primera vista
    return render(request, 'nosvemos.html', {"nombre":"Pedro"})
```

si la el directorio donde se encuentran las plantillas de la App es: **/templates/** no es necesario definir `TEMPLATES = [{'DIRS':['C:/plnatillas']}]` 

- ejemplo para **/templates/core/home.html**:

`return render(request, 'core/home.html', {"nombre":"Pedro"})` 

### plantillas incrustadas

 definir la plantilla **site/nav.html** y llamarla en la plantilla que será incrustada de la siguiente forma, donde desee que aparezca ` {% include "site/nav.html" %}`

### herencia de plantillas

` {% extends "padre.html" %}` debe ser la primera etiqueta de las plantillas hijas para que herenden 

![1594526664590](django\extends.png)

`{% block title %}{% endblock %}` - block define los bloques cambiantes en las plantillas

padre.html

```python
<head>   
    <title>{% block title %} {% endblock %}</title>
</head>
<body>
    <h1>Curso de Django</h1>
    <h3>uso de herencia plantillas</h3>
    {% block content %}{% endblock %}

    <footer>
        <p>Gracias por su visita</p>
    </footer>
</body>
</html>
```

hija.html

```python
{% extends "site/base.html" %}

{% block title %}
Titulo de Hija
{% endblock %}

{% block content %}

<p> contenido hija, tiempo: {{tiempo}}</p>

{% endblock %}
```

## Aplicación

### proyecto vs aplicacion

las **aplicaciones** forman parte de un **proyecto**, el cual puede tener muchas **aplicaciones** o ninguna, fomenta la reutilización de las **app**

![1594553107520](django\project vs app.png)

### primeros pasos

1. `python manage.py startapp gestionPedidios` - crea la **app** gestionPedidios

2. crear los modelos

3. registrar la app en el projecto en **/nombreProjecto/settings.py** `INSTALLED_APPS = []` insertar la app

   ```python
   INSTALLED_APPS = [
       'django.contrib.admin',
       ...
       'django.contrib.staticfiles',
       'gestionPedidos',  # app insertada
   ]
   ```

4. `python magana.py check gestionPedidos`  - chequea que la app gestionPedidos este instalada correctamente -- resp: issues (0 silenced)

5. `python manage.py makemigrations` crea la bd y genera el fichero de migraciones /migrations/0001_initials.py

6. `python manage.py sqlmigrate gestionPedidos 0001` -  crea el sql de la estructura de base de datos de una app (gestionPedidos) para una migración específica (0001)

7. `python manage.py migrate` - ejecuta todos los comando anteriores para crear las tablas en bd

### estructura

:file_folder: **gestionPedidios**	
	:file_folder: **migrations**
	:file_cabinet: **`__init__.py`** - para que python trate como un paquete **Proyecto1**
	:file_cabinet: **admin.py** - fichero que se registran los modelos para el panel de administración
	:file_cabinet: **apps.py** - fichero de configuración de la App 
	:file_cabinet: **models.py** - modelos de la app
	:file_cabinet: **test.py** - 
	:file_cabinet: **views.py** - gestión de vistas de la app

---

:file_cabinet: **admin.py** 

```python
from django.contrib import admin
from .models import Services

# Register your models here.
class ServicesAdmin(admin.ModelAdmin):
    readonly_fields = ('created', 'updated')
admin.site.register(Services, ServicesAdmin)
```

:file_cabinet: **apps.py**

```python
from django.apps import AppConfig

class ServicesConfig(AppConfig):
    name = 'services'
    verbose_name="Gestion de Servicios"

# Importar esta configuracion en /settings.py/
INSTALLED_APPS = [
    'django.contrib.admin',
    ...
    'core',
    'services.apps.ServicesConfig',
]
```

:file_cabinet: **models.py**

```python
from django.db import models

# Create your models here.
class Services(models.Model):
    title = models.CharField(max_length=200, verbose_name='Titulo')
    subtitle = models.CharField(max_length=200, verbose_name='Subtitulo')
    content = models.TextField(verbose_name='Contenido')
   # image = models.ImageField(verbose_name='Imagen', upload_to="services")
    created = models.DateField(auto_now_add=True, verbose_name='Fecha Creacion')
    updated = models.DateField(auto_now=True, verbose_name='Fecha Actualizacion')

    class Meta:
        verbose_name="servicios"
        verbose_name_plural="servicios"
        ordering=['-created']
    
    def __str__(self):
        return self.title
```

:file_cabinet: **views.py**

```python
from django.shortcuts import render

def home(req):
    return render(req,"core/home.html")
def about(req):
    return render(req, 'core/about.html')
def services(req):
    return render(req, 'core/services.html')
def store(req):
    return render(req, 'core/store.html')
```

### rutas app

1. crear ficero **:file_cabinet: urls.py** , `from importar django.url import path` 

2. crear el diccionario con el listado de rutas

3. importar las rutas de la app en las rutas del proyecto general, mediante la función `include()` de django.urls

   ```python
   from django.urls import path
   from . import views
   
   urlpatterns = [
       path('', views.home, name="home"),
       path('about/', views.about, name="about"),
       path('services/', views.services, name="services"),
       path('store/', views.store, name="store"),
       path('contact/', views.contact, name="contact"),
       path('blog/', views.blog, name="blog"),
       path('sample/', views.sample, name="sample")
   ]
   
   # URL projectp urls.py
   from django.contrib import admin
   from django.urls import path, include
   
   urlpatterns = [
       path('admin/', admin.site.urls),
       path('core/', include('core.urls')) #antepone la ruta /core/ a la ruta import
       path('', include('core.urls')) # elimina la ruta /core/ de las importadas
   ]
   ```

   

## Model(modelo)

**Class Model** - es la utilizada para gestionas el modelo de dato tipo ORM

Django no puede trabajar con **Model Class** sin crear una **aplicación**

para crear un **modelo** simplemente creamos una clase que herede de `Model Class` :file_cabinet:models.py

```python
from django.db import models

# Create your models here.

class Clientes(models.Model):
    nombre = models.CharField(max_length=30)
    direccion = models.CharField(max_length=50)
    email = models.EmailField()
    telefono = models.CharField(max_length=7)

class Articulos(models.Model):
    nombre = models.CharField(max_length=30)
    seccion = models.CharField(max_length=30)
    precio = models.IntegerField()

class Prdidos(models.Model):
    numero = models.IntegerField()
    fecha = models.DateField()
    entregado = models.BooleanField()
```

### manipular datos

1. `python manage.py shell`- abre el shell para escribir codigo

2. CRUD desde la consola

   ```shell
   #************** Create ***************
   from gestionPedidos.models import Articulos
   art = Articulos(nombre='mesa', seccion='decoracion', precio= 90)
   art.save()
   
   # create
   art2 = Articulos.objects.create(nombre='taladro', seccion='ferreteria', precio= 60)
   #************** Select ***************
   art3 = Articulos.objects.get(id=5)
   
   #************** Update ***************
   art.precio = 110
   art.save()
   
   #************** Delete ***************
   art.delete()
   ```

3. manejo de datos where

   **all**
   `art_all = Articulos.objects.all()`

   **muestra la consulta **
   `art_all.query.__str__() # retorna 'SELECT id, nombre FROM gestionPedidos'`

   **where - filter**
   `art5 = Articulos.objects.filter(seccion='deporte')`

   **Mayo(`__gte=`) y meno(`__lte=`) desde  shell**

   uso normal `Articulos.objects.filter(seccion='deporte', precio > 100)`

   en shell `Articulos.objects.filter(seccion='deporte', precio__gte=100, precio__lte=500)`

   **entre dos valores (`__range(2, 10)`**

   `art5 = Articulos.objects.filter(seccion='deporte',percio__range(2,10) )`

   **ordenar (`__order_by(campos)`)**

   `art5 = Articulos.objects.filter(percio__range(2,10) ).order_by('precio')`

   desc = `.order_by('-precio')` - colocar un signo (-)menos, delante del campo

### relations

**one-to-many** and **many-to-many**

```python
# one-to-one
author= models.OneToOneField(User, verbose_name="Autor", on_delete=models.CASCADE)

# one-to-many
author= models.ForeignKey(User, verbose_name="Autor", on_delete=models.CASCADE)

# many-to-many
categories= models.ManyToManyField(Category, verbose_name="Categorias")
```



### postgresSQL

**conexion** configurar settings.py

```python
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql_psycopg2',
        'NAME': 'ariculos',
        'USER': 'postgres',
        'PASSWORD': 'postgres',
        'HOST': 'localhost',
        'DATABASE_PORT': '5432',
    }
}
```

### otra migración

modificamos la clase Artículos y creamos la nueva migración con los cambios realizaos , siempre se deben ejecutar los comandos siguiente para poder actualizar los cambios en el modelo aunque no realicen modificación en la base de datos

1. `python manage.py makemigrations`
2. `python manage.py migrate`

```python
class Articulos(models.Model):
    nombre = models.CharField(max_length=30)
    seccion = models.CharField(max_length=30)
    precio = models.IntegerField()

    def __str__(self):
        return "Articulo => # %s | nombre(%s) | seccion(%s) | preio(%s)" %(self.nombre, self.seccion, self.precio)
```

## Panel de Administración

1. localhost:8000/admin - accede al panel de administración 
2. python manage.py createsuperuser

### reguistrar modelo en panel

1. en el :file_cabinet: **admin.py** de la App importar el modelo y registrarlo

   ```python
   from django.contrib import admin
   from .models import Project
   
   # Register your models here.
   admin.site.register(Project)
   ```

### configurar Modelo para Panel

verbose_name -> permite crear un alias para mostrar en las descripciones (campos, clases, nombre modelos etc.)

1. **/appname/apps.py**  - fichero de configuración de la App 

   ```python
   from django.apps import AppConfig
   
   class PortfolioConfig(AppConfig):
       name = 'portfolio'
       verbose_name = 'portafolio' 
   ```

2. poner la configuracion extendida en la lista de `INSTALLED_APPS` del proyecto **/projectname/settings.py** 

   ```python
   INSTALLED_APPS = [
       'django.contrib.admin',
       ...
       'core',
       'portfolio.apps.PortfolioConfig',
   ]
   ```

3. configurar el modelo para el panelde admin, **Class Meta:** define los metadatos mostrado, **`__str__(self)`** el texto mostrado en el listado de registros

   ```python
   from django.db import models
   
   class Project(models.Model):
       title = models.CharField(max_length=200, verbose_name="titulo")
       description = models.TextField(verbose_name="descripcion")    
       created = models.DateTimeField(auto_now_add=True,verbose_name="fecha creacion")
       updated = models.DateTimeField(auto_now=True, verbose_name="fecha actualizacion")
   
       class Meta:
           verbose_name = "proyecto"
           verbose_name_plural = "proyectos"
           ordering = ["-created"]
   
       def __str__(self):
           return self.title
   ```

4. personalizando campos a mostrar  **/appname/admin.py** 

   ```python
   from django.contrib import admin
   from .models import Project
   
   # Register your models here.
   class ProjectAdmin(admin.ModelAdmin):
       readonly_fields = ('created', 'updated')
   
   admin.site.register(Project, ProjectAdmin)
   ```

### Personalizar Panel

**/appname/admin.py** 

### Permiso de Grupos y User

- crear grupos y administrarlos visualmente en el panel de admin

- para personalizar al nivel de campos de los modelos, debemos crear las funciones que lo manejan en tiempo de ejecucion ej:

  ```python
  from django.contrib import admin
  from .models import Link
  
  class LinkAdmin(admin.ModelAdmin):
      readonly_fields =('created','updated')
  
      def get_readonly_fields(self, request, obj=None):
          if request.user.groups.filter(name="Personal").exists():
              return ('key','name')
          else:
              return ('created','updated')
  
  admin.site.register(Link, LinkAdmin)
  ```

## Configuración de Ficheros Media

1. crear directorio donde se guardaran los ficheros media **/madia/**

2. **/settings.py** incluir la configuración de la url y el directorio

```python
# media config
MEDIA_URL = '/media/'
MEDIA_ROOT = os.path.join(BASE_DIR, 'media')
```

3. configurar la url en **/url.py**, verificar si esta en modo DEBUG, importar los ficheros estáticos y asignar la url para estos ficheros con la configuración establecida en **/settings.py**

   ```python
   from django.contrib import admin
   from django.urls import path, include
   from django.conf import settings
   
   urlpatterns = [
       path('admin/', admin.site.urls),
       path('services/', include('services.urls')),
   ]
   
   if settings.DEBUG:
       from django.conf.urls.static import static
       urlpatterns += static(settings.MEDIA_URL, document_root= settings.MEDIA_ROOT)
   ```

## Formularios

1. crear **/forms.py** dentro definir el formulario

   ```python
   from django import forms
   
   class ContactForm(forms.Form):
       name = forms.CharField(label='Nombre', required=True)
       email = forms.EmailField(label='Correo', required=True)
       content = forms.CharField(label='Contenido', required=True, widget=forms.Textarea)
   ```

2. crear la instancia en **/views.py** para posteriormente utilizarla como una varible en el **template.html**

   ```python
   from django.shortcuts import render
   from .forms import ContactForm
   
   def contact(req):
       cont_form = ContactForm()
       return render(req, 'contact/contact.html',{'form': cont_form})
   ```

3. utilizarlo en **/template.html** como una variable mas

   ```python
    <table>
       {{ form.as_table }}
    </table>
   ```

4. creale el **<form>** y el <imput>  que no es creado por defecto, y generar el **token**

   ```html
    <form action="" method="">
        {% csrf_token %}
        <table>
            {{ form.as_table }}
        </table>
        <input type="submit" value="Enviar">
   </form>
   ```

5. el diccionario ***request*** contiene todos los valores del httprequest

   ```python
from django.shortcuts import render, redirect
   from django.urls import reverse
   from .forms import ContactForm
   
   def contact(req):
       cont_form = ContactForm()
       if req.method == 'POST':
           cont_form = ContactForm(data=req.POST)
           if cont_form.is_valid():
               return redirect(reverse('contact')+'?ok')
                       
       return render(req, 'contact/contact.html',{'form': cont_form})
   ```

### campos personalizados

se especifica la propiedad dentro del diccionario form para cada campo deseado

- **forms.py**

  ```python
  from django import forms
  
  class ContactForm(forms.Form):
      name = forms.CharField(label='Nombre', required=True, widget=forms.TextInput(
           attrs={'class':'form-control', 'placeholder':'Escriba su Nombre'},
      ))
      email = forms.EmailField(label='Correo', required=True, widget=forms.EmailInput(
           attrs={'class':'form-control', 'placeholder':'Escriba su Nombre'},
      ))
      content = forms.CharField(label='Contenido', required=True, widget=forms.Textarea(
           attrs={'class':'form-control', 'placeholder':'Escriba su Nombre'},
      ))
  ```

- **template.html**

  ```php+HTML
   <form action="" method="POST">
      {% csrf_token %}
      <div class="form-group">
      <label>Nombre *</label>
      <div class="input-group">
  
      {{ form.name }}
      </div>
      {{ form.name.errors }}
      </div>
      <div class="form-group">
      <label>Email *</label>
      <div class="input-group">
  
      {{ form.email }}
      </div>
      {{ form.email.errors }}
  
      </div>
      <div class="form-group">
      <label>Mensaje *</label>
      <div class="input-group">
  
      {{ form.content }}
      </div>
      {{ form.content.errors }}
      </div>
      <div class="text-center">
      <input type="submit" class="btn btn-primary btn-block py-2" value="Enviar">
      </div>
      </form>
  ```

  